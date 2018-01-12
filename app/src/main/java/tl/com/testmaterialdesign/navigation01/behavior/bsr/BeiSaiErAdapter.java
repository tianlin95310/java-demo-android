package tl.com.testmaterialdesign.navigation01.behavior.bsr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
        return 0;
    }

    class BeiViewHolder extends RecyclerView.ViewHolder
    {
        public BeiViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
