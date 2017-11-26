package tl.com.testmaterialdesign.navigation41;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation41.imagescale.ImageScaleActivity;
import tl.com.testmaterialdesign.navigation41.imgdivide.ImageDivideActivity;
import tl.com.testmaterialdesign.navigation41.mntimage.MntImageActivity;
import tl.com.testmaterialdesign.navigation41.paintshade.PaintShadeActivity;

/**
 * Created by tianlin on 2017/4/17.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Fragment41 extends Fragment implements View.OnClickListener
{

    LinearLayout linearLayout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment41, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.ll_41);

        for (int i = 0; i < linearLayout.getChildCount(); i++)
        {
            linearLayout.getChildAt(i).setOnClickListener(this);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_photo_scale:
                Intent intent = new Intent(getActivity(), ImageDivideActivity.class);
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

    @OnClick(R.id.bt_photo2)
    public void bt_photo2()
    {
        Intent intent = new Intent(getActivity(), ImageScaleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_photo3)
    public void bt_photo3()
    {
        Intent intent = new Intent(getActivity(), MntImageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_photo4)
    public void bt_photo4()
    {
        Intent intent = new Intent(getActivity(), PaintShadeActivity.class);
        startActivity(intent);
    }
}
