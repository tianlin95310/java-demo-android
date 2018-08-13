package tl.com.testmaterialdesign.navigation22;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation22.move.PopMoveActivity;
import tl.com.testmaterialdesign.navigation22.pop.PopWindowActivity;
import tl.com.testmaterialdesign.utils.ui.TLDatePickerFragment;
import tl.com.testmaterialdesign.utils.ui.TLDialogFragment;
import tl.com.testmaterialdesign.utils.ui.TLTimePickerFragment;

/**
 * Created by tianlin on 2017/3/16.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Fragment22 extends Fragment implements View.OnClickListener {
    LinearLayout linearLayout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment22, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.f6_ll);

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).setOnClickListener(this);
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f6_bt1a:

                /**
                 * 在show调用之后，TlDialogFragment才会开始创建
                 */
                TLDialogFragment
                        .newInstance()
                        .setStyleId(R.style.AppCompatAlertDialogStyle1)
                        .setTitle("提示")
                        .setMsg("你好吗？")
                        .setOkText("确认")
                        .setNoText("取消")
                        .setOkListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNoListener(null)
                        .show(getActivity().getSupportFragmentManager(), "alert");
                ;
                break;

            case R.id.f6_bt1b:

                /**
                 * 在show调用之后，TlDialogFragment才会开始创建
                 */
                TLDialogFragment
                        .newInstance()
                        .setStyleId(R.style.AppCompatAlertDialogStyle2)
                        .setTitle("提示")
                        .setMsg("你好吗？")
                        .setOkText("确认")
                        .setNoText("取消")
                        .setOkListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNoListener(null)
                        .show(getActivity().getSupportFragmentManager(), "alert");
                break;


            case R.id.f6_bt1c:

                /**
                 * 在show调用之后，TlDialogFragment才会开始创建
                 */
                TLDialogFragment
                        .newInstance()
                        .setStyleId(R.style.AppCompatAlertDialogStyle3)
                        .setTitle("提示")
                        .setMsg("你好吗？")
                        .setOkText("确认")
                        .setNoText("取消")
                        .setOkListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNoListener(null)
                        .show(getActivity().getSupportFragmentManager(), "alert");
                break;


            case R.id.f6_bt2: {

                TLDatePickerFragment
                        .newInstance()
                        .setDivider("/")
                        .setOnDateSetListener(new TLDatePickerFragment.OnTlDateSetListener() {
                            @Override
                            public void onDateSet(String date) {
                                Toast.makeText(getActivity(), "date = " + date, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getActivity().getSupportFragmentManager(), "date");
                break;
            }

            case R.id.f6_bt3:

                TLTimePickerFragment
                        .newInstance()
                        .setIs24(true)
                        .setDivider("\\")
                        .setOnTlTimeSetListener(new TLTimePickerFragment.OnTlTimeSetListener() {
                            @Override
                            public void onTimeSet(String time) {
                                Toast.makeText(getActivity(), "time = " + time, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getActivity().getSupportFragmentManager(), "time");
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.f22_bt4)
    public void f22_bt4() {

        Intent intent = new Intent(getActivity(), PopWindowActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.f22_bt5)
    public void f22_bt5() {

        Intent intent = new Intent(getActivity(), PopMoveActivity.class);
        startActivity(intent);
    }
}
