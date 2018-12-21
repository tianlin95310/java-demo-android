package tl.com.testmaterialdesign.navigation71.autoR;

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
import tl.com.testmaterialdesign.navigation71.pullrefresh.RefreshAdapter;
import tl.com.testmaterialdesign.navigation71.pullrefresh.RefreshVo;
import tl.com.testmaterialdesign.navigation71.pullrefresh.TLOnRefreshListener;
import tl.com.testmaterialdesign.navigation71.pullrefresh.TLRefreshRecyclerView;
import tl.com.testmaterialdesign.utils.toast.ToastUtils;

/**
 * Created by tianlin on 2018/12/18.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class AutoRefreshActivity extends BaseActivity implements TLOnRefreshListener{

    @BindView(R.id.recycler_view)
    TLRefreshRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_refresh);
        ButterKnife.bind(this);
        initView();
    }


    @Override
    public void onRefresh() {
        ToastUtils.show(this, "onRefresh");
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Log.d("my", "onRefresh");
                recyclerView.finish();
            }
        }, 2000);
    }

    @Override
    public void onLoad() {
        Log.i("my", "onLoad");
        response(this, "加载");
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Log.d("my", "onLoad");
                List<RefreshVo> refreshVos = new ArrayList<>();
                for(int i = 0; i < 5; i++) {
                    RefreshVo refreshVo = new RefreshVo();
                    refreshVo.viewType = RefreshAdapter.VIEW_TYPE_ITEM;
                    refreshVo.content = "new content" + i;
                    refreshVos.add(refreshVo);
                }
                RefreshAdapter refreshAdapter = (RefreshAdapter) recyclerView.getAdapter();
                int preSize = refreshAdapter.getItemCount();
                refreshAdapter.addList(refreshVos);

                recyclerView.smoothScrollToPosition(preSize);

                recyclerView.finish();
            }
        }, 2000);
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setTlOnRefreshListener(this);

        List<RefreshVo> refreshVos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RefreshVo refreshVo = new RefreshVo();
            refreshVo.viewType = RefreshAdapter.VIEW_TYPE_ITEM;
            refreshVo.content = "content" + i;
            refreshVos.add(refreshVo);
        }
        RefreshAdapter refreshAdapter = new RefreshAdapter(this, refreshVos);
        recyclerView.setAdapter(refreshAdapter);
    }
}
