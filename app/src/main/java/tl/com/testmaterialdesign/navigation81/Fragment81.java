package tl.com.testmaterialdesign.navigation81;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation81.realm.RealmTestActivity;
import tl.com.testmaterialdesign.navigation81.simplelabel.SimpleLabelActivity;
import tl.com.testmaterialdesign.navigation81.vue.VusJsActivity;

/**
 * Created by tianlin on 2017/7/17.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class Fragment81 extends Fragment
{
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment81, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_simple_view)
    public void bt_simple_view()
    {
        Intent intent = new Intent(getActivity(), SimpleLabelActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.bt_vue_js)
    public void bt_vue_js()
    {
        Intent intent = new Intent(getActivity(), VusJsActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.bt_realm)
    public void bt_realm()
    {
        Intent intent = new Intent(getActivity(), RealmTestActivity.class);
        startActivity(intent);
    }
}
