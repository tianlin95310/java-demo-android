package tl.com.testmaterialdesign.navigation11.longpressd;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.OnlyTextAdapter;
import tl.com.testmaterialdesign.utils.nullcheck.NullCheckUtils;

/**
 * Created by tianlin on 2017/7/14.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class LongPressedMoveActivity extends BaseActivity
{

    @BindView(R.id.rv_long_pressed)
    RecyclerView rvLongPressed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_pressed_move);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        rvLongPressed.setLayoutManager(new LinearLayoutManager(this));

        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 20; ++i)
        {
            strs.add("我是第" + (i + 1) + "个");
        }

        OnlyTextAdapter adapter = new OnlyTextAdapter(this, strs);
        rvLongPressed.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(callBack);
        touchHelper.attachToRecyclerView(rvLongPressed);
    }

    ItemTouchHelper.Callback callBack = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT)
    {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
        {
            Log.d("my", "callBack onMove");
            OnlyTextAdapter adapter = (OnlyTextAdapter) recyclerView.getAdapter();

            if (adapter == null)
                return false;

            List<String> strs = adapter.getStrings();

            if (NullCheckUtils.isEmpty(strs))
                return false;

            int fromPosition = viewHolder.getAdapterPosition();

            int toPosition = target.getAdapterPosition();

            Collections.swap(strs, fromPosition, toPosition);

            adapter.notifyItemMoved(fromPosition, toPosition);

            return true;
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
        {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
        {

        }
    };
}
