package tl.com.testmaterialdesign.navigation11.slidedelete;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;

/**
 * Created by tianlin on 2017/7/14.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class SlideDeleteActivity extends BaseActivity
{
    @BindView(R.id.rv_slide_delete)
    RecyclerView rvSlideDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_delete);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        rvSlideDelete.setLayoutManager(new LinearLayoutManager(this));

        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 20; ++i)
        {
            strs.add("我是第" + (i + 1) + "个");
        }

        OnlyTextAdapter adapter = new OnlyTextAdapter(this, strs);
        rvSlideDelete.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(callBack);
        touchHelper.attachToRecyclerView(rvSlideDelete);
    }
    ItemTouchHelper.Callback callBack = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
        {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
        {
            Log.d("my", "onSwiped, direction = " + direction + " position = " + viewHolder.getLayoutPosition());
        }
    };


}
