package tl.com.testmaterialdesign.navigation71.pullrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;
import tl.com.testmaterialdesign.utils.toast.ToastUtils;

/**
 * Created by tianlin on 2017/10/19.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class TLPullRefActivity extends BaseActivity implements TLRefreshRecyclerView.TLOnRefreshListener
{
    @BindView(R.id.recycler_view)
    TLRefreshRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tl_pull_ref);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setTlOnRefreshListener(this);
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 15; i++)
        {
            list1.add("left string " + (i + 1));
        }
        OnlyTextAdapter adapter1 = new OnlyTextAdapter(this, list1);
        recyclerView.setAdapter(adapter1);
    }

    @Override
    public void onRefresh()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ToastUtils.show(TLPullRefActivity.this, "onRefresh");
                Log.d("my", "onRefresh");
                recyclerView.finish();
            }
        }, 2000);
    }

    @Override
    public void onLoad()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Log.d("my", "onLoad");
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 15; i++)
                {
                    list.add("new String " + (i + 1));
                }
                OnlyTextAdapter onlyTextAdapter = (OnlyTextAdapter) recyclerView.getAdapter();
                onlyTextAdapter.addList(list);

                recyclerView.finish();
            }
        }, 2000);
    }
}