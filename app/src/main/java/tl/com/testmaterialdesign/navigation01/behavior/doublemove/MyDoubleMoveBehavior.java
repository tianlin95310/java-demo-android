package tl.com.testmaterialdesign.navigation01.behavior.doublemove;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/10/17.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class MyDoubleMoveBehavior extends CoordinatorLayout.Behavior<View>
{
    public MyDoubleMoveBehavior()
    {
    }

    public MyDoubleMoveBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return 只有数值方向的滑动并且是带有behavior的才算
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed  给consumed[1]赋值后，dyUnconsumed in onNestedScroll = dy in onNestedPreScroll - consumed[1] in onNestedPreScroll
     *
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed)
    {

        if(child.getId() == target.getId())
        {
            RecyclerView recyclerView = (RecyclerView) coordinatorLayout.findViewById(R.id.rv2);
            recyclerView.scrollBy(0, dy);
        }
        else
        {
            RecyclerView recyclerView = (RecyclerView) child;
            recyclerView.scrollBy(0, dy);
        }

//        一旦消费了dy以后，子View就没有对应的dy偏移量了，导致子View没有偏移量，无法滑动， consumed是返回参数,size = 2，他会返回给子View消费了多少
//        consumed[1] = dy;
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
    {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX
     * @param velocityY
     * @return 返回false不消费事件,返回true表示behavior消费了事件，导致孩子没有滑动的效果
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY)
    {
        if(child.getId() == target.getId())
        {
            RecyclerView recyclerView = (RecyclerView) coordinatorLayout.findViewById(R.id.rv2);
            recyclerView.fling((int)velocityX, (int) velocityY);
        }
        else
        {
            RecyclerView recyclerView = (RecyclerView) child;
            recyclerView.fling((int)velocityX, (int) velocityY);
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed)
    {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

}
