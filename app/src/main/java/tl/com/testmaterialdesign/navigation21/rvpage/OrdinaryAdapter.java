package tl.com.testmaterialdesign.navigation21.rvpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.utils.display.PixsUtils;

/**
 * Created by tianlin on 2018/7/31.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class OrdinaryAdapter extends RecyclerView.Adapter {
    List<DataBean> dataBeans;

    Context context;

    public OrdinaryAdapter(List<DataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false);
        return new OrdinaryAdapter.MyViewHolder(view);
    }
    private void setVisible(RecyclerView.ViewHolder viewHolder, boolean visibility) {
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();

        if(layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(0, 0);
        }
        if (visibility) {
            viewHolder.itemView.setVisibility(View.VISIBLE);
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = PixsUtils.dp2px(context, 100);
        } else {
            viewHolder.itemView.setVisibility(View.GONE);
            layoutParams.width = 0;
            layoutParams.height = 0;
        }
        viewHolder.itemView.setLayoutParams(layoutParams);
    }

    /**
     * 隐藏item，会导致整个View并没有收缩，如果是顶端的会导致下拉刷新无效
     * @param holder
     * @param position
     */
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrdinaryAdapter.MyViewHolder viewHolder = (MyViewHolder) holder;
        DataBean data = dataBeans.get(position);

        if(data.isShow) {
            setVisible(holder, true);
        }
        else {
            setVisible(holder, false);
        }

        viewHolder.text1.setText(String.valueOf(data.content));
        viewHolder.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBeans.get(holder.getAdapterPosition()).isShow = false;
                notifyItemChanged(holder.getAdapterPosition());

                RVPagingActivity activity  = (RVPagingActivity) context;
                activity.refresh();
            }
        });
    }

    /**
     * 正常删除
     * @param holder
     * @param position
     */
    public void onBindViewHolder1(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrdinaryAdapter.MyViewHolder viewHolder = (MyViewHolder) holder;
        DataBean data = dataBeans.get(position);

        viewHolder.text1.setText(String.valueOf(data.content));
        viewHolder.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBeans.remove(viewHolder.getAdapterPosition());
                notifyItemRemoved(viewHolder.getAdapterPosition());


            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;

        public MyViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.text);
        }
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }
}
