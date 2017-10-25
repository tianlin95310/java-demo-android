package tl.com.testmaterialdesign.navigation31.recyclereuse;

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

/**
 * Created by tianlin on 2017/7/20.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class RecyclerReuseActivity extends BaseActivity
{
    @BindView(R.id.rv_recycle_reuse)
    RecyclerView rvRecycleReuse;

    @Override
    public void initView()
    {
        List<MyString> strs = new ArrayList<>();

        for(int i =  0; i < 40; i++)
        {
            strs.add(new MyString());
        }
        rvRecycleReuse.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this, strs);

        rvRecycleReuse.setAdapter(adapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_reuse);
        ButterKnife.bind(this);
        
        initView();
    }
}
