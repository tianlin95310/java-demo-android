package tl.com.testmaterialdesign.service.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import io.realm.Realm;
import tl.com.testmaterialdesign.database.realm.DiffProgressReadAndWrite;

/**
 * Created by tianlin on 2017/7/14.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 如果启动Service的进程和Service的进程不同，那么他们IBinder类也会不同
 */

public class BindStartService extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d("my", "BindStartService onBind 我通过Intent发送数据"+ intent.getStringExtra("request") + "给Service，只能发送一次");

        MyBinder myBinder = new MyBinder();

        Log.d("my", "onBind class = " + myBinder.getClass());

        // 保存一个数据到数据库，在主进程去读取
        saveOneDataInADiffPro();

        return myBinder;
    }

    private void saveOneDataInADiffPro()
    {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                DiffProgressReadAndWrite database = new DiffProgressReadAndWrite();
                database.setTimestamp(1000);
                database.setModTimeStamp(2000);
                realm.copyToRealm(database);
            }
        });

        realm.close();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.d("my","BindStartService onCreate" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d("my","BindStartService onStartCommand" );
        return super.onStartCommand(intent, flags, startId);
    }

}
