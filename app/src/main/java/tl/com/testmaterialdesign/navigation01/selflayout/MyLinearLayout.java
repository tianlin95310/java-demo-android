package tl.com.testmaterialdesign.navigation01.selflayout;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by tianlin on 2017/10/12.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class MyLinearLayout extends LinearLayout
{

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    Rect rect1;
    Rect rect2;

    public MyLinearLayout(Context context)
    {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        int childCount = getChildCount();

        if (childCount == 2)
        {
            recyclerView1 = (RecyclerView) getChildAt(0);
            recyclerView2 = (RecyclerView) getChildAt(1);
        }
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        // 该方法会布局孩子，因为他是LinearLayout已经写好的方法
        super.onLayout(changed, l, t, r, b);

        // 在布局了孩子后，后面的getLeft等方法才会有效
        rect1 = new Rect();
        rect1.left = recyclerView1.getLeft();
        rect1.top = recyclerView1.getTop();
        rect1.right = recyclerView1.getRight();
        rect1.bottom = recyclerView1.getBottom();

        rect2 = new Rect();
        rect2.left = recyclerView2.getLeft();
        rect2.top = recyclerView2.getTop();
        rect2.right = recyclerView2.getRight();
        rect2.bottom = recyclerView2.getBottom();

    }

    // 只要子View消费了事件DOWN事件，那么事件就会往下分发，所以父布局的事件分发是会走的
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {

        move(ev);
        return super.dispatchTouchEvent(ev);
    }

    public void move(MotionEvent ev)
    {
        Point point = new Point();
        point.x = (int) ev.getRawX();
        point.y = (int) ev.getRawY();
        if (rect1.contains(point.x, point.y))
        {
            recyclerView2.dispatchTouchEvent(ev);
        } else if (rect2.contains(point.x, point.y))
        {
            recyclerView1.dispatchTouchEvent(ev);
        }
    }

}
