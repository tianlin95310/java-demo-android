package tl.com.testmaterialdesign.navigation71.shanxing;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.utils.ui.TLShanXingRatioView;

/**
 * Created by tianlin on 2018/4/13.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class ShanXingRatioActivity extends BaseActivity
{
    @BindView(R.id.tl_shanxing)
    TLShanXingRatioView tlShanxing;

    @Override
    public void initView()
    {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanxing);
        ButterKnife.bind(this);
    }
}
