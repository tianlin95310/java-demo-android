package tl.com.testmaterialdesign.navigation01.behavior.titlebar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.utils.display.DensityUtils;
import tl.com.testmaterialdesign.utils.display.ScreenUtils;


/**
 * Created by tianlin on 2017/12/14.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class TitleBarBehavior extends AppBarLayout.Behavior implements AppBarLayout.OnOffsetChangedListener {
    CoordinatorLayout parent;

    AppCompatActivity appCompatActivity;

    public TitleBarBehavior() {
    }

    public TitleBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.appCompatActivity = (AppCompatActivity) context;

    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout child, @NonNull View dependency) {
        if (this.parent == null) {
            this.parent = parent;
            child.addOnOffsetChangedListener(this);
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
    {
        Log.d("my", "onOffsetChanged verticalOffset = " + verticalOffset);
        float per = 0.2f / (20 * DensityUtils.getScreenDensity(appCompatActivity));
        float scale = 1 + verticalOffset * per;
        TextView textView = appBarLayout.findViewById(R.id.tv_title);
        textView.setPivotX(0);
        textView.setPivotY(textView.getHeight() / 2);
        textView.setScaleX(scale);
        textView.setScaleY(scale);

    }
}
