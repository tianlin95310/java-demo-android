package tl.com.testmaterialdesign.navigation01.floatintop;

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
import tl.com.testmaterialdesign.base.IBaseView;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;

/**
 * Created by tianlin on 2017/9/25.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class FloatInTopActivity extends BaseActivity implements IBaseView
{
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_in_top);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        recycler.setLayoutManager(new LinearLayoutManager(this));

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++)
        {
            list.add("string " + (i + 1));
        }

        OnlyTextAdapter adapter = new OnlyTextAdapter(this, list);
        recycler.setAdapter(adapter);
    }
}
