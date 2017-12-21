package tl.com.testmaterialdesign.navigation61.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/12/14.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class MyAppBarLayoutBav extends AppBarLayout.Behavior implements AppBarLayout.OnOffsetChangedListener
{
    // 下滑过程中的最大距离
    private static float MAX_SLIDE = 480;

    private static float MIN_WIDTH = 0;

    // 横纵变化的比率
    private static float SCALE = 0;

    CoordinatorLayout parent;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;

    // 平移量
    float offset_h;

    Context context;
    public MyAppBarLayoutBav() {
        super();
    }

    public void setDragable (final boolean canDrag) {
        setDragCallback(new DragCallback()
        {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout)
            {
                return canDrag;
            }
        });
    }

    public MyAppBarLayoutBav(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDragable(true);

        this.context = context;

    }


    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, AppBarLayout child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed)
    {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    /**
     * 在偏移量发生变化时才会调用，verticalOffset，初始值为0
     * @param appBarLayout
     * @param verticalOffset
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
    {
//        toolbar.setTranslationY(appBarLayout.getTotalScrollRange() + verticalOffset);
//        Log.d("my", "onOffsetChanged verticalOffset = " + verticalOffset);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev)
    {
//        Log.d("my", "onInterceptTouchEvent ev = " + ev.toString());
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    float startY;
    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev)
    {
//        Log.d("my", "onTouchEvent ev = " + ev.toString());

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:

                float dy = ev.getRawY() - startY;

                setAppBarLayoutParam(dy);

                startY = ev.getRawY();

                break;
        }
        return super.onTouchEvent(parent, child, ev);
    }

    public void setAppBarLayoutParam(float dy)
    {
        offset_h = offset_h + dy;

        if(offset_h >= MAX_SLIDE)
            offset_h = MAX_SLIDE;

        if(offset_h < 0)
            offset_h = 0;

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        layoutParams.width = parent.getWidth() - (int)(offset_h / SCALE);

        if(layoutParams.width <= MIN_WIDTH)
            layoutParams.width = (int) MIN_WIDTH;
        if(layoutParams.width >= parent.getWidth())
            layoutParams.width = parent.getWidth();
        appBarLayout.setLayoutParams(layoutParams);

        Log.d("my", "dy = " + dy + ", offset_h = " + offset_h + ", layoutParams.width = " + layoutParams.width);

        appBarLayout.setTranslationY(offset_h);
        recyclerView.setTranslationY(offset_h);

        recyclerView.setAlpha(1 - offset_h / MAX_SLIDE);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection)
    {
        Log.d("my", "onLayoutChild");

        boolean result = super.onLayoutChild(parent, abl, layoutDirection);
        if(appBarLayout == null) {
            appBarLayout = abl;
            this.parent = parent;
            toolbar = (Toolbar) parent.findViewById(R.id.toolbar);
            recyclerView = (RecyclerView) parent.findViewById(R.id.recycler_view);
            appBarLayout.addOnOffsetChangedListener(this);

            toolbar.setPadding(0, 56, 0, 0);
            // 初始化布局参数
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
            layoutParams.width = parent.getWidth();
            abl.setLayoutParams(layoutParams);

            // 窗口的最小宽度
            MIN_WIDTH = layoutParams.width * 0.8f;

            // 窗口减到最小宽度移动的距离
            float dy = parent.getWidth() - MIN_WIDTH;

            SCALE = MAX_SLIDE / dy;
            Log.d("my", "parent.getWidth() = " + parent.getWidth());
            Log.d("my", "MIN_WIDTH = " + MIN_WIDTH);
            Log.d("my", "SCALE = " + SCALE);

            abl.setTranslationY(offset_h);
            recyclerView.setTranslationY(offset_h);
        }

        return result;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency)
    {
        Log.d("my", "onDependentViewChanged");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes)
    {
        Log.d("my", "onStartNestedScroll directTargetChild = " + directTargetChild.getClass().getSimpleName() + ", target = " + target.getClass().getSimpleName());
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes)
    {
        Log.d("my", "onNestedScrollAccepted");
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed)
    {
        Log.d("my", "onNestedPreScroll dy = " + dy + ", consumed[1] = " + consumed[1]);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
    {
        Log.d("my", "onNestedScroll dyConsumed = " + dyConsumed);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target)
    {
        Log.d("my", "onStopNestedScroll");
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY)
    {
        Log.d("my", "onNestedPreFling");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed)
    {
        Log.d("my", "onNestedFling");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}
