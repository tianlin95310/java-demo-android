package tl.com.testmaterialdesign.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import tl.com.testmaterialdesign.MainActivity;

/**
 * Created by tianlin on 2017/6/21.
 * Tel : 15071485690
 * QQ : 953108373
 * Function : 测试发现Android6.0以上无法重启
 */

public class AutoStartReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(intent == null)
            return;

        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
        {
            Intent startIntent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

            Log.i("my", "AutoStartReceiver start MainActivity");
        }

    }
}
