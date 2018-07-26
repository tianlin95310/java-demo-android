package tl.com.testmaterialdesign.navigation21.rvpage;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tianlin on 2018/7/11.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class RvPageAdapter extends PagedListAdapter<DataBean, RvPageAdapter.MyViewHolder> {

    private Context context;

    public RvPageAdapter(DiffUtil.ItemCallback<DataBean> mDiffCallback, Context context) {
        super(mDiffCallback);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataBean data = getItem(position);
        holder.text1.setText(String.valueOf(data.content));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;

        public MyViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(android.R.id.text1);
            text1.setTextColor(Color.RED);
        }
    }
}
