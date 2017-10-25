package tl.com.testmaterialdesign.navigation01.behavior.pullrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;
import tl.com.testmaterialdesign.utils.toast.ToastUtils;

/**
 * Created by tianlin on 2017/10/18.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class PullRefreshActivity extends BaseActivity implements TLPullRefreshRecycleView.OnRefreshListener
{
    @BindView(R.id.ll_refresh)
    LinearLayout llRefresh;
    @BindView(R.id.rv)
    TLPullRefreshRecycleView rv;

    @Override
    public void initView()
    {
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            list1.add("string " + (i + 1));
        }
        OnlyTextAdapter adapter1 = new OnlyTextAdapter(this, list1);
        rv.setAdapter(adapter1);

        rv.setOnRefreshListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void onRefresh()
    {

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                // 主线程
                ToastUtils.show(PullRefreshActivity.this, "onRefresh");
                rv.close();
            }
        }, 2000);

    }

}
