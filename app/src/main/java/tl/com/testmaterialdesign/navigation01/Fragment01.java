package tl.com.testmaterialdesign.navigation01;

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
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation01.behavior.BehaviorActivity;
import tl.com.testmaterialdesign.navigation01.bsr.BeiSaiErActivity;
import tl.com.testmaterialdesign.navigation01.floatintop.FloatInTopActivity;
import tl.com.testmaterialdesign.navigation01.gaussblur.GaussianBlurActivity;
import tl.com.testmaterialdesign.navigation01.liaotian.LiaotianActivity;
import tl.com.testmaterialdesign.navigation01.searchview.SearchViewActivity;
import tl.com.testmaterialdesign.navigation01.selflayout.SelfLayoutActivity;
import tl.com.testmaterialdesign.navigation01.useapi.UseApiActivity;

/**
 * Created by tianlin on 2017/3/6.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Fragment01 extends Fragment
{

    @BindView(R.id.f01_fab4)
    Button f01Fab4;
    @BindView(R.id.f01_fab1)
    Button f01Fab1;
    @BindView(R.id.f01_fab3)
    Button f01Fab3;
    @BindView(R.id.f01_fab5)
    Button f01Fab5;
    @BindView(R.id.f01_fab6)
    Button f01Fab6;
    @BindView(R.id.f01_fab7)
    Button f01Fab7;
    @BindView(R.id.f01_fab8)
    Button f01Fab8;
    @BindView(R.id.f01_fab9)
    Button f01Fab9;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment01, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.f01_fab1, R.id.f01_fab3})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.f01_fab1:
                Intent intent1 = new Intent(getActivity(), LiaotianActivity.class);
                startActivity(intent1);
                break;
            case R.id.f01_fab3:
                Intent intent = new Intent(getActivity(), SearchViewActivity.class);
                startActivity(intent);
                break;
        }
    }


    @OnClick(R.id.f01_fab4)
    public void f01_fab4()
    {
        Intent intent = new Intent(getActivity(), FloatInTopActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.f01_fab5, R.id.f01_fab6, R.id.f01_fab7})
    public void secondGroup(View view)
    {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.f01_fab5:
                intent = new Intent(getActivity(), BehaviorActivity.class);
                startActivity(intent);
                break;
            case R.id.f01_fab6:
                intent = new Intent(getActivity(), SelfLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.f01_fab7:
                intent = new Intent(getActivity(), UseApiActivity.class);
                startActivity(intent);

                break;
        }

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @OnClick(R.id.f01_fab8)
    public void f01_fab8()
    {
        Intent intent = new Intent(getContext(), BeiSaiErActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.f01_fab9)
    public void f01_fab9()
    {
        Intent intent = new Intent(getContext(), GaussianBlurActivity.class);
        startActivity(intent);
    }
}
