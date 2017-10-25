package tl.com.testmaterialdesign.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by tianlin on 2017/4/20.
 * Tel : 15071485692
 * QQ : 953108373
 * Function : 已制定的DPI来decode图片，貌似只能对应用内部的图片有效
 *          sdcard上的图片无效
 */

public class DecodeBmpWithDPI
{
    /**
     *
     * @param context
     * @param imgID
     * @param densityDPI
     * @return
     */
    public static Bitmap getBmp(Context context, int imgID, int densityDPI)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = densityDPI;
        return BitmapFactory.decodeResource(context.getResources(), imgID, options);
    }

    public static Bitmap getBmp(Context context, String imgPath, int densityDPI)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = densityDPI;
        return BitmapFactory.decodeFile(imgPath, options);
    }
}
