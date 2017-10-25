package tl.com.testmaterialdesign.utils.anim;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by tianlin on 2017/6/30.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 属性动画工具，以下函数，每两个一组，实现平移和回到原来的位置
 */

public class AnimUtils
{

    // 放大到原本大小(顶部方向)
    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        // 设置动画的中心点
        ViewCompat.setPivotX(view, view.getWidth() / 2);
        ViewCompat.setPivotY(view, 0);
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }
    // 缩小到消失(顶部方向)
    public static void scaleHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.setPivotX(view, view.getWidth() / 2);
        ViewCompat.setPivotY(view, 0);
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    /**
     * 水平平移
     * @param dx 平移的距离
     * @param viewPropertyAnimatorListener
     */
    public static void translateX(View view, int dx, ViewPropertyAnimatorListener viewPropertyAnimatorListener)
    {
        ViewCompat.animate(view)
                .translationX(dx)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .start();

    }

    /**
     * 向上平移
     * @param dy
     * @param viewPropertyAnimatorListener
     * @param duration
     */
    public static void translateY(View view, int dy, ViewPropertyAnimatorListener viewPropertyAnimatorListener, long duration)
    {
        ViewCompat.animate(view)
                .translationY(dy)
                .setDuration(duration)
                .setListener(viewPropertyAnimatorListener)
                .start();

    }

}
