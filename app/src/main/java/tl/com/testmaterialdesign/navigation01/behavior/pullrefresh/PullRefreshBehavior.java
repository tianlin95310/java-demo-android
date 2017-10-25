package tl.com.testmaterialdesign.navigation01.behavior.pullrefresh;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/10/18.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class PullRefreshBehavior extends CoordinatorLayout.Behavior<View>
{

    Context context;

    View dep;
    int dep_h;

    int max_dep;

    TextView textView;

    TLPullRefreshRecycleView recycler;

    public PullRefreshBehavior()
    {
    }

    Handler handler = new Handler();

    public PullRefreshBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        max_dep = (int) context.getResources().getDimension(R.dimen.ll_refresh_h);
        dep_h = max_dep - 32;
    }

    /**
     * 先调用了CoordinatorLayout的onLayout后，才会调用该方法
     *
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection)
    {
        Log.d("my", "onLayoutChild");
        dep = parent.findViewById(R.id.ll_refresh);
        textView = (TextView) parent.findViewById(R.id.tv_refresh);
        // 将按上面的一块放到屏幕上方
        dep.layout(0, -dep_h, parent.getWidth(), 0);
        // 将列表占满屏幕
        child.layout(0, 0, parent.getWidth(), parent.getHeight());

        // 为TLPullRefreshRecycleView设置behavior
        recycler = (TLPullRefreshRecycleView) child;
        recycler.setPullRefreshBehavior(this);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
    {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
    {
        Log.d("my", "layoutDependsOn");
        return child instanceof RecyclerView;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed)
    {

        // 滑动方式1

        RecyclerView recyclerView = (RecyclerView) child;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int first = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

        if (recycler.getTranslationY() >= dep_h)
        {
            textView.setText("松开刷新");
        }
        else
        {
            textView.setText("下拉刷新");
        }
        // 如果没有到顶端，不准滑动
        if (first != 0)
            return;
        // 下滑
        if (dy < 0)
        {
            // 平移刷新view
            float dyDep = dep.getTranslationY() - dy;
            if (dyDep >= 0 && dyDep <= max_dep)
            {
                dep.setTranslationY(dyDep);
            }

            // 平移列表
            float dyRecycle = child.getTranslationY() - dy;
            if (dyRecycle >= 0 && dyRecycle <= max_dep)
            {
                child.setTranslationY(dyRecycle);
            }
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
    {
//        if(recycler.getTranslationY() >= dep_h)
//        {
//            textView.setText("松开刷新");
//        }
//        // 滑动方式2
//        if (dyConsumed == 0 && dyUnconsumed < 0)
//        {
//            Log.d("my", "到边界了，还在下滑");
////             平移刷新view
//            float dyDep = dep.getTranslationY() - dyUnconsumed;
//            if (dyDep >= 0 && dyDep <= max_dep)
//            {
//                dep.setTranslationY(dyDep);
//            }
////             平移列表
//            float dyRecycle = child.getTranslationY() - dyUnconsumed;
//            if (dyRecycle >= 0 && dyRecycle <= max_dep)
//            {
//                child.setTranslationY(dyRecycle);
//            }
//        }
    }

    boolean isAnim = false;

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, final View child, View target)
    {

        if (child.getTranslationY() >= dep_h)
        {
            textView.setText("正在刷新");
            refresh();
        } else
            close();
    }

    private void refresh()
    {
        if (recycler.getOnRefreshListener() != null)
            recycler.getOnRefreshListener().onRefresh();
    }

    public void close()
    {
        textView.setText("刷新完成");

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                recycler.setTranslationY(0);
                dep.setTranslationY(0);
            }
        }, 500);
//        if (!isAnim)
//        {
//            AnimUtils.translateY(dep, 0, new ViewPropertyAnimatorListener()
//            {
//                @Override
//                public void onAnimationStart(View view)
//                {
//                    if (recycler.getTranslationY() >= dep_h)
//                    {
//                        textView.setText("刷新完成");
//                        refresh();
//                    }
//
//                    isAnim = true;
//                }
//
//                @Override
//                public void onAnimationEnd(View view)
//                {
//                    isAnim = false;
//                    textView.setText("下拉刷新");
//                }
//
//                @Override
//                public void onAnimationCancel(View view)
//                {
//                    isAnim = false;
//                }
//            }, (long) Math.abs(recycler.getTranslationY()));
//
//            AnimUtils.translateY(recycler, 0, null, (long) Math.abs(recycler.getTranslationY()));
//        }
    }
}
