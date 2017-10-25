package tl.com.testmaterialdesign.navigation01.behavior.test;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

//
//         MyCoordinatorLayout  onMeasure
//         MyBehavior           layoutDependsOn --- child = 2131624070, dependency = 2131624071
//         MyBehavior           onMeasureChild
//         MyRecyclerView       onMeasure
//         MyCoordinatorLayout  onMeasure
//         MyBehavior           layoutDependsOn --- child = 2131624070, dependency = 2131624071
//         MyBehavior           onMeasureChild
//         MyRecyclerView       onMeasure
//         MyCoordinatorLayout  onLayout
//         MyBehavior           onLayoutChild
//         MyRecyclerView       onLayout
//         MyBehavior           layoutDependsOn --- child = 2131624070, dependency = 2131624071
//         MyBehavior           onDependentViewChanged

/**
 * Created by tianlin on 2017/10/13.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class MyBehavior extends CoordinatorLayout.Behavior<View>
{

    public MyBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Log.d("my", "MyBehavior");
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev)
    {
//        Log.d("my", "MyBehavior onInterceptTouchEvent");
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev)
    {
//        Log.d("my", "MyBehavior onTouchEvent");
        return super.onTouchEvent(parent, child, ev);
    }

    /**
     *  layoutDependsOn会先于onMeasureChild调用，在测量的时候会调用两次
     * @param parent
     * @param child 是赋予behavior的控件
     * @param dependency
     * @return 返回true才能正常建立依赖关系，初始化时就会调用一次onDependentViewChanged，以后的每次滑动都会调用layoutDependsOn和onDependentViewChanged
     * 返回false的话，初始化测量的时候会调用一下layoutDependsOn，之后滑动时就不会在调用layoutDependsOn和onDependentViewChanged
     *
     * 并且返回false的话，在onLayoutChild中不会刷新dependency的布局，导致dependency的getHeight时有时无，返回true能解决问题
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
    {
        Log.d("my", "MyBehavior layoutDependsOn --- child = " + child.getId() + ", dependency = " + dependency.getId());
        return child instanceof RecyclerView;

//        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed)
    {
        Log.d("my", "MyBehavior onMeasureChild");
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    /**
     *
     * @param parent
     * @param child
     * @param layoutDirection
     * @return 返回true使用自己的布局，返回false使用CoordinatorLayout默认的布局
     * 这里需要注意的是所以得测量工作已经完成，即所有的view，包括parent，child，他们的getMeasuredHeight有值
     * getHeight的值时有时无，因为getHeight的值实在他们的layout调用完之后才有的，包括getLeft,getTop等等
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection)
    {
        Log.d("my", "MyBehavior onLayoutChild");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
    {
        Log.d("my", "MyBehavior onDependentViewChanged");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency)
    {
        Log.d("my", "MyBehavior onDependentViewRemoved");
        super.onDependentViewRemoved(parent, child, dependency);
    }

    /**
     *
     * @param coordinatorLayout
     * @param child         带有behavior的view
     * @param directTargetChild 当前滑动的view，他不一定是带有behavior的view，coordinatorLayout其他的孩子滑动也会触发该方法
     * @param target            当前滑动的view，可多级
     * @param nestedScrollAxes
     * @return 返回true才会调用接下来的一系列的滑动方法
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {
        Log.d("my", "MyBehavior onStartNestedScroll");
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 && child.getId() == target.getId();
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {
        Log.d("my", "MyBehavior onNestedScrollAccepted");
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * 在没有嵌套父子View滚动时child和target是相同的
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed)
    {
        Log.d("my", "MyBehavior onNestedPreScroll child = " + child.getId() + ", target = " + target.getId() + ", dy = " + dy);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    /**
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed    孩子已经消费的
     * @param dyConsumed
     * @param dxUnconsumed  孩子没有消费的
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
    {
        Log.d("my", "MyBehavior onNestedScroll child = " + child.getId() + ", target = " + target.getId() + ", dyConsumed = " + dyConsumed + ", dyUnconsumed = " + dyUnconsumed);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target)
    {
        Log.d("my", "MyBehavior onStopNestedScroll");
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY)
    {
        Log.d("my", "MyBehavior onNestedPreFling");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed)
    {
        Log.d("my", "MyBehavior onNestedFling");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }




}
