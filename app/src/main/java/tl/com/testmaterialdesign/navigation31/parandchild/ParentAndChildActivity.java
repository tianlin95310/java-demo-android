package tl.com.testmaterialdesign.navigation31.parandchild;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseVo;

public class ParentAndChildActivity extends AppCompatActivity
{

    @BindView(R.id.rv_list1)
    RecyclerView rvList1;
    @BindView(R.id.rv_list2)
    RecyclerView rvList2;
    @BindView(R.id.rv_list3)
    RecyclerView rvList3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_and_child);
        ButterKnife.bind(this);

        initView();
    }

    private void initView()
    {
        rvList1.setLayoutManager(new LinearLayoutManager(this));
        rvList2.setLayoutManager(new LinearLayoutManager(this));
        rvList3.setLayoutManager(new LinearLayoutManager(this));

        List<BaseVo> baseVoList1 = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            BaseVo baseVo = new BaseVo();
            baseVo.viewType = 1;
            baseVoList1.add(baseVo);
        }

        List<BaseVo> baseVoList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            BaseVo baseVo = new BaseVo();
            baseVo.viewType = 1;
            baseVoList2.add(baseVo);
        }

        ParAndChildAdapter adapter1 = new ParAndChildAdapter(this, baseVoList1);
        rvList1.setAdapter(adapter1);

        ParAndChildAdapter adapter2 = new ParAndChildAdapter(this, baseVoList2);
        rvList2.setAdapter(adapter2);

        ParAndChildAdapter adapter3 = new ParAndChildAdapter(this, baseVoList1);
        rvList3.setAdapter(adapter3);

    }
}
