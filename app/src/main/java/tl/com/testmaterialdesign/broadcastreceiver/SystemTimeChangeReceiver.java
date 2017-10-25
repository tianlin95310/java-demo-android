package tl.com.testmaterialdesign.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tianlin on 2017/6/16.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 应用退出后无法收到广播
 */

public class SystemTimeChangeReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(intent == null)
            return;

        if(Intent.ACTION_TIME_CHANGED.equals(intent.getAction()))
        {
            Log.e("my", "SystemTimeChangeReceiver");
            Toast.makeText(context, "SystemTimeChangeReceiver", Toast.LENGTH_LONG).show();
        }

    }
}
