package tl.com.testmaterialdesign.navigation91;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

/**
 * Created by tianlin on 2017/7/20.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class Fragment91 extends Fragment
{
    Unbinder unbinder;
    @BindView(R.id.bt_get_path)
    Button btGetPath;
    @BindView(R.id.bt_get_sys_time)
    Button btGetSysTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment91, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_get_path)
    public void onViewClicked()
    {
        getPathData(getActivity());
    }

    /**
     * 目录相关
     */
    private void getPathData(Context context)
    {

        // /storage/emulated/0
        Log.d("my", "getAbsolutePath = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        // /data apk目录
        Log.d("my", "getDataDirectory = " + Environment.getDataDirectory().getAbsolutePath());
        // /system 系统目录
        Log.d("my", "getRootDirectory = " + Environment.getRootDirectory().getAbsolutePath());
        // /cache
        Log.d("my", "getDownloadCacheDirectory = " + Environment.getDownloadCacheDirectory().getAbsolutePath());
        // mounted
        Log.d("my", "getExternalStorageState = " + Environment.getExternalStorageState());

        // 内置存储卡里的文件路径
        Log.d("my", "getFilesDir = " + context.getFilesDir().getAbsolutePath());
        // 位置存储卡的缓存路径
        Log.d("my", "getCacheDir = " + context.getCacheDir().getAbsolutePath());
        // 会在外置卡的/Android/data里面生成一个cache文件夹
        Log.d("my", "getExternalCacheDir = " + context.getExternalCacheDir().getAbsolutePath());
        // 会在外置内存卡的/Android/data里面生成一个files文件，并在里面生成DCIM文件夹
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Log.d("my", "getExternalFilesDirs = " + context.getExternalFilesDirs(Environment.DIRECTORY_DCIM)[0].getAbsolutePath());
        }
    }

    @OnClick(R.id.bt_get_sys_time)
    public void bt_get_sys_time()
    {
        getSystemTime();
    }

    /**
     * 获取系统时间，若系统的时间被修改，这里获取的值也是被修改的
     */
    public void getSystemTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        Log.d("my", "time = " + sdf.format(new Date()));
    }
}
