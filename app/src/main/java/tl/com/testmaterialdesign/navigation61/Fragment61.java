package tl.com.testmaterialdesign.navigation61;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation61.fullanddialog.DialogThemeActivity;
import tl.com.testmaterialdesign.navigation61.marktext.MarkTextActivity;
import tl.com.testmaterialdesign.navigation61.callfun.CallNotForeFunActivity;
import tl.com.testmaterialdesign.service.TimingService;
import tl.com.testmaterialdesign.service.bindservice.BindStartService;

import static tl.com.testmaterialdesign.service.TimingService.timstamp;

/**
 * Created by tianlin on 2017/6/26.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class Fragment61 extends Fragment
{
    Unbinder unbinder;

    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            // 防止fragment被干掉造成空指针
            if (timstamp != 0 && btBeginClock != null)
                btBeginClock.setText(sdf.format(new Date(timstamp)));
        }
    };

    Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            // 造成的延迟是因为消息是一个一个处理的，延迟的是Activity
            handler.sendEmptyMessage(1);
            handler.postDelayed(runnable, 1000);
        }
    };
    @BindView(R.id.bt_begin_clock)
    Button btBeginClock;
    @BindView(R.id.bt_bind)
    Button btBind;
    @BindView(R.id.bt_send_data_to_service)
    Button btSendDataToService;
    @BindView(R.id.bt_get_service_data)
    Button btGetServiceData;
    @BindView(R.id.bt_fun4)
    Button btFun4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment61, container, false);

        unbinder = ButterKnife.bind(this, view);

        updateUI();

        return view;
    }

    private void updateUI()
    {
        handler.post(runnable);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_begin_clock)
    public void bt_begin_clock()
    {
        Intent intent = new Intent(getActivity(), TimingService.class);
        getActivity().startService(intent);
    }

    @OnClick({R.id.bt_bind, R.id.bt_send_data_to_service, R.id.bt_get_service_data})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.bt_bind:
                Intent intent = new Intent(getActivity(), BindStartService.class);
                intent.putExtra("request", "request");
                getActivity().bindService(intent, new MyConnection(), Activity.BIND_AUTO_CREATE);
                break;
            case R.id.bt_send_data_to_service:
                break;
            case R.id.bt_get_service_data:
                break;
        }
    }

    @OnClick(R.id.bt_mark_text)
    public void bt_mark_text()
    {
        Intent intent = new Intent(getActivity(), MarkTextActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_hide_intent)
    public void bt_hide_intent()
    {
        Intent intent = new Intent();
        intent.setAction("aaa.bbb.ccc");

        startActivity(intent);
    }

    @OnClick(R.id.bt_hide_intent2)
    public void bt_hide_intent2()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        startActivity(intent);
    }

    @OnClick(R.id.bt_fun3)
    public void bt_fun3()
    {
        Intent intent = new Intent(getActivity(), DialogThemeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_fun4)
    public void bt_fun4()
    {
        Intent intent = new Intent(getActivity(), CallNotForeFunActivity.class);
        startActivity(intent);
    }

    class MyConnection implements ServiceConnection
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.d("my", "onServiceConnected service class = " + service.getClass());

        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.d("my", "MyConnection onServiceDisconnected");
        }
    }
}
