package tl.com.testmaterialdesign.navigation01.behavior.floataction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;
import tl.com.testmaterialdesign.utils.anim.AnimUtils;
import tl.com.testmaterialdesign.utils.toast.ToastUtils;

/**
 * Created by tianlin on 2017/6/29.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class FloatActionActivity extends AppCompatActivity
{
    @BindView(R.id.rv_floataction)
    RecyclerView rvFloataction;
    @BindView(R.id.fab_to_top)
    FloatingActionButton fabToTop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_floataction);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        AnimUtils.translateX(fabToTop, fabToTop.getWidth(), null, 800);
    }

    private void initView()
    {
        rvFloataction.setLayoutManager(new LinearLayoutManager(this));

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++)
        {
            list.add("string " + (i + 1));
        }

        OnlyTextAdapter adapter = new OnlyTextAdapter(this, list);
        rvFloataction.setAdapter(adapter);
    }

    @OnClick(R.id.fab_to_top)
    public void onViewClicked()
    {
        ToastUtils.show(this, "fab_to_top click");
        rvFloataction.smoothScrollToPosition(1);
    }
}
