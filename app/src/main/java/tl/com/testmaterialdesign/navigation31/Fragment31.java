package tl.com.testmaterialdesign.navigation31;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation31.inputlimit.InputLimitActivity;
import tl.com.testmaterialdesign.navigation31.parandchild.ParentAndChildActivity;
import tl.com.testmaterialdesign.navigation31.recyclereuse.RecyclerReuseActivity;
import tl.com.testmaterialdesign.navigation31.viewanim.ViewAnimActivity;

/**
 * Created by tianlin on 2017/3/18.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Fragment31 extends Fragment
{

    @BindView(R.id.bt_input_limit)
    Button btInputLimit;
    @BindView(R.id.bt_recycler_reuse)
    Button btRecyclerReuse;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment31, container, false);
        initView();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView()
    {

    }

    @OnClick({R.id.bt_input_limit, R.id.bt_recycler_reuse, R.id.bt_parent_child, R.id.bt_view_anim})
    public void onViewClicked(View view)
    {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.bt_input_limit:
                intent = new Intent(getActivity(), InputLimitActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_recycler_reuse:
                intent = new Intent(getActivity(), RecyclerReuseActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_parent_child:
                intent = new Intent(getActivity(), ParentAndChildActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_view_anim:
                intent = new Intent(getActivity(), ViewAnimActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

}
