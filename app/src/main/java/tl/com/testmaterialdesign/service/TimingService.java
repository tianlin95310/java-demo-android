package tl.com.testmaterialdesign.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tl.com.testmaterialdesign.utils.nullcheck.NullCheckUtils;

/**
 * Created by tianlin on 2017/6/26.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 计时服务
 */

public class TimingService extends Service
{
    public static long timstamp;

    // 定时器
    Timer timer;

    // 每隔一秒，时间戳增加1000ms
    TimerTask timing = new TimerTask()
    {
        @Override
        public void run()
        {
            timstamp += 1000;

            Log.d("my", "TimingService is run timstamp = " + timstamp + ", time = "+ sdf.format(new Date(timstamp)));
        }
    };

    // 每2分钟校验一次时间
    TimerTask checkCurrentApp = new TimerTask()
    {
        @Override
        public void run()
        {
            checkCurrentApp();
        }
    };

    /**
     * 判断当前前台运行的应用
     */
    private void checkCurrentApp()
    {
        ActivityManager mActivityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(5);

        if(!NullCheckUtils.isEmpty(rti))
        {
            ActivityManager.RunningTaskInfo runningTaskInfo = rti.get(0);

            Log.d("my", "当前处于前台的应用是：" + runningTaskInfo.topActivity.getPackageName() + ", " + runningTaskInfo.topActivity.getClassName());
        }
    }

    // 每30分钟更新一次时间戳
    TimerTask updateTime = new TimerTask()
    {
        @Override
        public void run()
        {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        // 获取手机时钟时间
        Log.d("my", "getTimeInMillis = " + Calendar.getInstance().getTimeInMillis());
        Log.d("my", "currentTimeMillis = " + System.currentTimeMillis());

        // 进程启动时间
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            Log.d("my", "getStartUptimeMillis = " + Process.getStartUptimeMillis());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            Log.d("my", "getStartElapsedRealtime = " + Process.getStartElapsedRealtime());
        }
        // cpu持续时间
        Log.d("my", "getElapsedCpuTime = " + Process.getElapsedCpuTime());
        // 主线程持续时间
        Log.d("my", "currentThreadTimeMillis = " + SystemClock.currentThreadTimeMillis());

        // 获取开机时间
//        SystemClock.elapsedRealtime();
    }

    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if(timer == null)
        {
            timer = new Timer();
//            timer.schedule(timing, 0, 1000);

            timer.schedule(checkCurrentApp, 0, 2000);
        }

        // 初始化时间
        long timstamp = Calendar.getInstance().getTimeInMillis();
        Log.d("my", "init time" + sdf.format(new Date(timstamp)));

        setTimstamp(timstamp);

        // 在不kill应用时，START_STICKY能让服务自启动，不管服务是在主进程还是其他进程
        return START_STICKY;
    }

    public long getTimstamp()
    {
        return timstamp;
    }

    public void setTimstamp(long timstamp)
    {
        this.timstamp = timstamp;
    }

}
