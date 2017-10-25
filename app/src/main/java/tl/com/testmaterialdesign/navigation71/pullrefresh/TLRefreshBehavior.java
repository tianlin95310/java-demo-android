package tl.com.testmaterialdesign.navigation71.pullrefresh;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.utils.anim.AnimUtils;

/**
 * Created by tianlin on 2017/10/19.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class TLRefreshBehavior extends CoordinatorLayout.Behavior<View>
{
    

    // 控件能下滑的最大位移量,即刷新块的高度
    int MAX_REFRESH_LAYOUT_HEIGHT;
    // 控件开始出现松开刷新的高度，达到该高度时，控件应该能继续下滑一段时间
    int BEGIN_REFRESH_HEIGHT;

    // 视图包装
    RefreshViewHolder refreshViewHolder;

    // 列表视图
    TLRefreshRecyclerView recyclerView;

    // 关闭动画是否正在进行
    boolean isAnimRunning = false;

    // 当前是刷新还是加载更多
    int mode = 0;
    public TLRefreshBehavior()
    {
    }

    public TLRefreshBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    /**
     * 该方法返回true，才会刷新dependency的布局，特别是在这样onLayoutChild返回true的情况下，onLayoutChild中的refreshViewHolder.ll_refresh才能正常的布局
     * 他们的getHeight才能正常，否则出现getHeight时有时无的情况，导致对他的布局无效
     * @param parent
     * @param child
     * @param dependency
     * @return 通常返回true
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
    {
        return child instanceof TLRefreshRecyclerView;
    }

    /**
     *
     * @param parent
     * @param child
     * @param layoutDirection
     * @return 在返回true的情况下，对于那些使用behavior的孩子，我们需要自己去布局他，否则控件无法显示，没有加behavior能正常显示
     *
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection)
    {
        // 初始化列表和监听器
        initRecyclerView(child);
        // 初始化View
        initViewHolder(parent);
        // 对孩子进行布局
        initAndLayoutChild(parent);

        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {
        return child instanceof TLRefreshRecyclerView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed)
    {

        if(isAnimRunning)
            return;

        // 下滑
        if(dy < 0)
        {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

            // 当前是第一个item
            if(position == 0)
            {
                float transition = recyclerView.getTranslationY() - dy;
                if(transition >= 0 && transition <= MAX_REFRESH_LAYOUT_HEIGHT)
                {
                    refreshViewHolder.ll_refresh.setTranslationY(transition);
                    recyclerView.setTranslationY(transition);

                    if(transition >= BEGIN_REFRESH_HEIGHT)
                    {
                        mode = 1;
                        refreshViewHolder.tv_refresh.setText("松开刷新");
                    }
                }
            }
        }
        else if(dy > 0)
        {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = linearLayoutManager.findLastCompletelyVisibleItemPosition();

            if(position == linearLayoutManager.getItemCount() - 1)
            {
                float transition = recyclerView.getTranslationY() - dy;

                if(transition >= -MAX_REFRESH_LAYOUT_HEIGHT && transition <= 0)
                {
                    refreshViewHolder.ll_load.setTranslationY(transition);
                    recyclerView.setTranslationY(transition);

                    if(transition <= -BEGIN_REFRESH_HEIGHT)
                    {
                        mode = 2;
                        refreshViewHolder.tv_load.setText("松开加载");
                    }
                }
            }
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target)
    {

        if(isAnimRunning)
            return;

        float transition = Math.abs(recyclerView.getTranslationY());
        if(transition >= BEGIN_REFRESH_HEIGHT)
        {
            if(mode == 1)
            {
                refreshViewHolder.tv_refresh.setText("正在刷新");
                if(recyclerView.getTlOnRefreshListener() != null)
                {
                    recyclerView.getTlOnRefreshListener().onRefresh();
                }
            }
            else if(mode == 2)
            {
                refreshViewHolder.tv_load.setText("正在加载");
                if(recyclerView.getTlOnRefreshListener() != null)
                {
                    recyclerView.getTlOnRefreshListener().onLoad();
                }
            }

        }
        else
        {
            finish();
        }

    }

    public void finish()
    {
        float translationY = recyclerView.getTranslationY();
        if(translationY == 0)
            return;

        if(!isAnimRunning)
        {
            long duration = (long)Math.abs(translationY * 2);

            AnimUtils.translateY(recyclerView, 0, new ViewPropertyAnimatorListener()
            {
                @Override
                public void onAnimationStart(View view)
                {
                    if(mode == 1)
                        refreshViewHolder.tv_refresh.setText("刷新完成");
                    else if(mode == 2)
                        refreshViewHolder.tv_load.setText("加载完成");
                    isAnimRunning = true;
                }
                @Override
                public void onAnimationEnd(View view)
                {
                    isAnimRunning = false;
                    if(mode == 1)
                        refreshViewHolder.tv_refresh.setText("下拉刷新");
                    else if(mode == 2)
                        refreshViewHolder.tv_load.setText("上拉加载");
                    mode = 0;
                }
                @Override
                public void onAnimationCancel(View view)
                {

                }
            }, duration);

            if(mode == 1)
                AnimUtils.translateY(refreshViewHolder.ll_refresh, 0, null, duration);
            else if(mode == 2)
                AnimUtils.translateY(refreshViewHolder.ll_load, 0, null, duration);
            else if(mode == 0)
            {
                AnimUtils.translateY(refreshViewHolder.ll_refresh, 0, null, duration);
                AnimUtils.translateY(refreshViewHolder.ll_load, 0, null, duration);
            }
        }

    }

    private void initRecyclerView(View child)
    {
        recyclerView = (TLRefreshRecyclerView) child;
        recyclerView.setTlRefreshBehavior(this);
    }

    private void initViewHolder(CoordinatorLayout parent)
    {
        if(refreshViewHolder == null)
        {
            refreshViewHolder = new RefreshViewHolder();
            refreshViewHolder.ll_refresh = (LinearLayout) parent.findViewById(R.id.ll_refresh);
            refreshViewHolder.pb_refresh = (ProgressBar) parent.findViewById(R.id.pb_refresh);
            refreshViewHolder.tv_refresh = (TextView) parent.findViewById(R.id.tv_refresh);

            refreshViewHolder.ll_load = (LinearLayout) parent.findViewById(R.id.ll_load);
            refreshViewHolder.pb_load = (ProgressBar) parent.findViewById(R.id.pb_load);
            refreshViewHolder.tv_load = (TextView) parent.findViewById(R.id.tv_load);
        }
    }

    private void initAndLayoutChild(CoordinatorLayout parent)
    {
        // 在进行布局时，此时肯定都是测量过年的，所以getMeasuredHeight是有值的
        MAX_REFRESH_LAYOUT_HEIGHT = refreshViewHolder.ll_refresh.getMeasuredHeight();
        BEGIN_REFRESH_HEIGHT = MAX_REFRESH_LAYOUT_HEIGHT * 3 / 4;
        refreshViewHolder.ll_refresh.layout(0, -MAX_REFRESH_LAYOUT_HEIGHT, refreshViewHolder.ll_refresh.getMeasuredWidth(), 0);
        refreshViewHolder.ll_load.layout(0, parent.getMeasuredHeight(), refreshViewHolder.ll_load.getMeasuredWidth(), parent.getMeasuredHeight() + MAX_REFRESH_LAYOUT_HEIGHT);
        recyclerView.layout(0, 0, recyclerView.getMeasuredWidth(), recyclerView.getMeasuredHeight());
    }

    class RefreshViewHolder
    {
        LinearLayout ll_refresh;
        ProgressBar pb_refresh;
        TextView tv_refresh;

        LinearLayout ll_load;
        ProgressBar pb_load;
        TextView tv_load;
    }
}
