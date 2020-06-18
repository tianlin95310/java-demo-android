package tl.com.testmaterialdesign.navigation01.behavior.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2017/10/13.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class BottomSheetActivity extends BaseActivity
{
    @BindView(R.id.frame_content)
    LinearLayout frameContent;

    BottomSheetBehavior bottomSheetBehavior;
    @BindView(R.id.bt_bottom)
    Button btBottom;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {

        bottomSheetBehavior = BottomSheetBehavior.from(frameContent);
//        设置折叠时的高度，如果为0，意味着折叠时隐藏
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.collapsed_h));
//        初始状态为折叠状态，源码可看到
        Log.d("my", "初始getState = " + bottomSheetBehavior.getState());
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
        {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState)
            {
                Log.d("my", "newState = " + newState);
                if (newState == BottomSheetBehavior.STATE_HIDDEN)
                    llBottom.setVisibility(View.VISIBLE);
                else
                {
                    llBottom.setVisibility(View.GONE);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset)
            {
                Log.d("my", "slideOffset = " + slideOffset);
            }
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

//        下滑时能影藏对话框
        bottomSheetBehavior.setHideable(true);
    }


    @OnClick(R.id.bt_bottom)
    public void bt_bottom()
    {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
