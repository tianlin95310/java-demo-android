package tl.com.testmaterialdesign.navigation01.bsr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.navigation01.bsr.bubble.BubbleActivity;
import tl.com.testmaterialdesign.navigation01.bsr.cyclebubblewave.CycleBubbleWaveActivity;
import tl.com.testmaterialdesign.navigation01.bsr.element.ElementAddShopActivity;
import tl.com.testmaterialdesign.navigation01.bsr.flowwater.PathFlowWaterActivity;
import tl.com.testmaterialdesign.navigation01.bsr.zhanlianbubble.WaveViewActivity;

/**
 * Created by tianlin on 2017/12/22.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class BeiSaiErActivity extends BaseActivity {
    @BindView(R.id.bt_element_add_shop)
    Button btElementAddShop;
    @BindView(R.id.bt_float_water)
    Button btFloatWater;
    @BindView(R.id.bt_bubble)
    Button btBubble;
    @BindView(R.id.bt_zhan_bubble)
    Button btZhanBubble;
    @BindView(R.id.bt_cycle_bubble)
    Button btCycleBubble;

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_sai_er);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_element_add_shop)
    public void bt_element_add_shop() {
        Intent intent = new Intent(this, ElementAddShopActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_float_water)
    public void bt_float_water() {
        Intent intent = new Intent(this, PathFlowWaterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_bubble)
    public void bt_bubble() {
        Intent intent = new Intent(this, BubbleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_zhan_bubble)
    public void bt_zhan_bubble() {
        Intent intent = new Intent(this, WaveViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_cycle_bubble)
    public void bt_cycle_bubble() {
        Intent intent = new Intent(this, CycleBubbleWaveActivity.class);
        startActivity(intent);
    }
}

