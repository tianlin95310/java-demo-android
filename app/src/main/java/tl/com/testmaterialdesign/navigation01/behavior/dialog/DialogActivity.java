package tl.com.testmaterialdesign.navigation01.behavior.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;

/**
 * Created by tianlin on 2017/11/30.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class DialogActivity extends BaseActivity
{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {

        toolbar.setTitle("dialogActivity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            list1.add("string " + (i + 1));
        }
        OnlyTextAdapter adapter1 = new OnlyTextAdapter(this, list1);
        recyclerView.setAdapter(adapter1);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
