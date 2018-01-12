package tl.com.testmaterialdesign.navigation11.gridrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/12/23.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    List<GridVo> voList;
    Context context;

    public GridAdapter(List<GridVo> voList, Context context)
    {
        this.voList = voList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        GridVo gridVo = voList.get(position);

        GridViewHolder viewHolder = (GridViewHolder) holder;
        viewHolder.img.setImageResource(gridVo.path);
    }

    @Override
    public int getItemCount()
    {
        return voList.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.img)
        ImageView img;

        public GridViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            view = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        }
    }
}
