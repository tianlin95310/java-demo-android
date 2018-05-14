package tl.com.testmaterialdesign.navigation71.pullrefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.utils.toast.ToastUtils;

/**
 * Created by tianlin on 2018/5/14.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class RefreshAdapter extends RecyclerView.Adapter
{

    public static final int VIEW_TYPE_ITEM = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    Context context;
    List<RefreshVo> refreshVoList;

    public RefreshAdapter(Context context, List<RefreshVo> refreshVoList)
    {
        this.context = context;
        this.refreshVoList = refreshVoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        if(viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_just_one_text, parent, false);
            return new MyViewHolder(view);
        }
        else if(viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.item_load_more, parent, false);
            return new LoadViewHolder(view);
        }

        return null;
    }

    public void addList(List<RefreshVo> refreshVos)
    {
        int preSize = this.refreshVoList.size();
        this.refreshVoList.addAll(preSize, refreshVos);

        notifyItemRangeInserted(preSize, refreshVos.size());
    }

    class LoadViewHolder extends RecyclerView.ViewHolder{
        public LoadViewHolder(View itemView)
        {
            super(itemView);
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.item_text)
        TextView itemText;

        public MyViewHolder(View itemView)
        {
            super(itemView);
//            View view = LayoutInflater.from(context).inflate(R.layout.item_just_one_text, parent, false);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_text)
        public void onViewClicked()
        {
            ToastUtils.show(context, refreshVoList.get(getAdapterPosition()).content);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        int viewType = getItemViewType(position);

        if(viewType == VIEW_TYPE_ITEM) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.itemText.setText(this.refreshVoList.get(position).content);
        }

    }

    @Override
    public int getItemViewType(int position)
    {
        return refreshVoList.get(position).viewType;
    }

    @Override
    public int getItemCount()
    {
        return refreshVoList.size();
    }
}
