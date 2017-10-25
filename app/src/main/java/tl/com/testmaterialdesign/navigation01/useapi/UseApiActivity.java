package tl.com.testmaterialdesign.navigation01.useapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;

/**
 * Created by tianlin on 2017/10/13.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class UseApiActivity extends BaseActivity
{
    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_use_api);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        rv1.setLayoutManager(new LinearLayoutManager(this));
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            list1.add("left string " + (i + 1));
        }
        OnlyTextAdapter adapter1 = new OnlyTextAdapter(this, list1);
        rv1.setAdapter(adapter1);

        rv2.setLayoutManager(new LinearLayoutManager(this));
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < 30; i++)
        {
            list2.add("right string " + (i + 1));
        }
        OnlyTextAdapter adapter2 = new OnlyTextAdapter(this, list2);
        rv2.setAdapter(adapter2);

        rv1.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if(recyclerView.getScrollState() != recyclerView.SCROLL_STATE_IDLE)
                    rv2.scrollBy(dx, dy);
            }
        });

        rv2.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if(recyclerView.getScrollState() != recyclerView.SCROLL_STATE_IDLE)
                    rv1.scrollBy(dx, dy);
            }
        });
    }
}
