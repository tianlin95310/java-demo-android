package tl.com.testmaterialdesign.navigation01.bsr.flowwater;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2018/3/26.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class PathFlowWaterActivity extends BaseActivity {
    @BindView(R.id.pfv)
    PathFlowView pfv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_water);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
    }
}
