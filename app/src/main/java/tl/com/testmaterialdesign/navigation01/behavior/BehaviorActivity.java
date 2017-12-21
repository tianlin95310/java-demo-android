package tl.com.testmaterialdesign.navigation01.behavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.navigation01.behavior.bottomsheet.BottomSheetActivity;
import tl.com.testmaterialdesign.navigation01.behavior.doublemove.BehaviorMoveActivity;
import tl.com.testmaterialdesign.navigation01.behavior.floataction.FloatActionActivity;
import tl.com.testmaterialdesign.navigation01.behavior.likeuc.LikeUCHomeActivity;
import tl.com.testmaterialdesign.navigation01.behavior.pullrefresh.PullRefreshActivity;
import tl.com.testmaterialdesign.navigation01.behavior.test.TestFunCallActivity;

/**
 * Created by tianlin on 2017/10/13.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class BehaviorActivity extends BaseActivity
{
    @BindView(R.id.f01_fab2)
    Button f01Fab2;
    @BindView(R.id.bt_3)
    Button bt3;
    @BindView(R.id.bt_1)
    Button bt1;
    @BindView(R.id.bt_4)
    Button bt4;
    @BindView(R.id.bt_5)
    Button bt5;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.bt_6)
    Button bt6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {

    }

    @OnClick({R.id.f01_fab2, R.id.bt_3})
    public void onViewClicked(View view)
    {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.f01_fab2:
                intent = new Intent(this, FloatActionActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_3:
                break;
        }
    }

    @OnClick(R.id.bt_1)
    public void bt_1()
    {
        Intent intent = new Intent(this, BottomSheetActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_3)
    public void bt_3()
    {
        Intent intent = new Intent(this, BehaviorMoveActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.bt_4, R.id.bt_5})
    public void bt_group_3(View view)
    {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.bt_4:
                intent = new Intent(this, LikeUCHomeActivity.class);

                startActivity(intent);
                break;
            case R.id.bt_5:

                intent = new Intent(this, PullRefreshActivity.class);

                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.bt)
    public void bt()
    {
        Intent intent = new Intent(this, TestFunCallActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_6)
    public void bt_6()
    {
    }
}
