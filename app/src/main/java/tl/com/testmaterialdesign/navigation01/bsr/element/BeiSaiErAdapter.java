package tl.com.testmaterialdesign.navigation01.bsr.element;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseVo;

/**
 * Created by tianlin on 2017/12/25.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class BeiSaiErAdapter extends RecyclerView.Adapter
{

    List<BaseVo> list;
    Context context;

    public BeiSaiErAdapter(List<BaseVo> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bei_sai_er, parent, false);

        return new BeiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class BeiViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.bt_beisaier)
        Button btBeisaier;

        public BeiViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            View view = LayoutInflater.from(context).inflate(R.layout.item_bei_sai_er, parent, false);
        }

        @SuppressLint("ResourceType")
        @OnClick(R.id.bt_beisaier)
        public void onViewClicked()
        {
            int position[] = new int[2];
            btBeisaier.getLocationOnScreen(position);

            Point start = new Point();
            start.x = position[0];
            start.y = position[1];
            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(start);
            }
        }
    }

    public OnItemClickListener getOnItemClickListener()
    {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Point start);
    }
}
