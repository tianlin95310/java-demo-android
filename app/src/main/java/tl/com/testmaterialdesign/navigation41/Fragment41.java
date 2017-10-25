package tl.com.testmaterialdesign.navigation41;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/4/17.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Fragment41 extends Fragment implements View.OnClickListener
{

    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment41, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.ll_41);

        for(int i = 0; i < linearLayout.getChildCount(); i++)
        {
            linearLayout.getChildAt(i).setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_photo_scale:
                Intent intent = new Intent(getActivity(), Activity401.class);
                startActivity(intent);
                break;

        }
    }
}
