package tl.com.testmaterialdesign.navigation01.behavior.bsr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2017/12/22.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class BeiSaiErActivity extends BaseActivity
{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void initView()
    {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_sai_er);
        ButterKnife.bind(this);
    }
}

