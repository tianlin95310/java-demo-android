package tl.com.testmaterialdesign.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by tianlin on 2017/6/29.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */


public class OnlyTextAdapter extends RecyclerView.Adapter<OnlyTextAdapter.MyViewHolder>
{
    Context context;
    List<String> strings;

    public OnlyTextAdapter(Context context, List<String> strings)
    {
        this.context = context;
        this.strings = strings;
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
            ToastUtils.show(context, strings.get(getAdapterPosition()));
            Log.d("my", strings.get(getAdapterPosition()));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_just_one_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        holder.itemText.setText(strings.get(position));
    }

    public void addList(List<String> list)
    {
        this.strings.addAll(list);
        notifyDataSetChanged();
    }

    public List<String> getStrings()
    {
        return strings;
    }

    public void setStrings(List<String> strings)
    {
        this.strings = strings;
    }

    @Override
    public int getItemCount()
    {
        return strings.size();
    }
}

