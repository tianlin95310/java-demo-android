package tl.com.testmaterialdesign.app;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by tianlin on 2017/7/14.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 每当为应用的组件指定一个新的进程，TestApplication的onCreate都会执行一次
 */

public class TestApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d("my", "TestApplication onCreate, myPid = " + Process.myPid());
        Log.d("my", "TestApplication onCreate, myTid = " + Process.myTid());
        Log.d("my", "TestApplication onCreate, myUid = " + Process.myUid());
        initRealm();

    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        Log.d("my", "TestApplication attachBaseContext");
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        Log.d("my", "TestApplication onLowMemory");
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        Log.d("my", "TestApplication onTerminate");
    }

    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        Log.d("my", "TestApplication onTrimMemory");
    }

    private void initRealm() {

        Realm.init(this);

        // 配置realm
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .migration(new RealmMigration()
                {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion)
                    {

                    }
                })
                .build();
        Realm.setDefaultConfiguration(config);
    }



}
