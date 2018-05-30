package tl.com.testmaterialdesign.utils.anim;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by tianlin on 2017/6/30.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 属性动画工具
 */

public class AnimUtils
{

    // 放大到原本大小(顶部方向)
    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(0);
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
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(0);
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
     * @param duration
     */
    public static void translateX(View view, int dx, ViewPropertyAnimatorListener viewPropertyAnimatorListener, long duration)
    {
        ViewCompat.animate(view)
                .translationX(dx)
                .setDuration(duration)
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

    /**
     * 透明度动画
     * @param view
     * @param alpha
     * @param viewPropertyAnimatorListener
     * @param duration
     */
    public static void alpha(View view, float alpha, long duration,
                             ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener,     // 位置更新监听
                             ViewPropertyAnimatorListener viewPropertyAnimatorListener)
    {
        ViewCompat.animate(view)
                .alpha(alpha)
                .setDuration(duration)
                .setUpdateListener(viewPropertyAnimatorUpdateListener)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }

    public static void scale(View view, float scaleX, float scaleY, ViewPropertyAnimatorListener viewPropertyAnimatorListener, long duration)
    {
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);

        ViewCompat.animate(view)
                .scaleX(scaleX)
                .scaleY(scaleY)
                .setDuration(duration)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }


}
