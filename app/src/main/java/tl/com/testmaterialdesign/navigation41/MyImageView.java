package tl.com.testmaterialdesign.navigation41;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/4/19.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class MyImageView extends AppCompatImageView
{
    // 原始图片
    Bitmap raw_bmp;
    // 改变密度的图片
    Bitmap scaleBmp;
    // 赋值的图片
    Bitmap copy;
    // 灰度图片
    Bitmap alpha;
    // drawable
    Bitmap drawable;

    int w;
    int h;

    Paint paint;

    public MyImageView(Context context)
    {
        super(context);
    }
    public MyImageView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        paint = new Paint();
        paint.setAlpha(66);

        // 1，加载原始图片大小
        raw_bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.man_under);

//        // 2，加载缩小的图片
        BitmapFactory.Options option = new BitmapFactory.Options();
//        option.inTargetDensity = DensityUtils.getScreenDensityDPI(context) / 6;
        option.inTargetDensity = 160;           // 画出的图片的实际密度

//        option.inScaled = true;                   // 允许图片放缩
        scaleBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.man_under, option);
//
//        // 3, 得到原始的透明图
//        alpha = raw_bmp.extractAlpha();
//        Log.d("my", "alpha sameAs bmp = " + raw_bmp.sameAs(alpha));
//        // 4，用原始图片赋值图片
//        copy = raw_bmp.copy(Bitmap.Config.ARGB_4444, true);
//        Log.d("my", "copy sameAs bmp = " + raw_bmp.sameAs(copy));
//
//        // 6,获取drawable转化为
//        drawable = ((BitmapDrawable)getDrawable()).getBitmap();
//
        getBmpInfo(raw_bmp);
        Log.d("my", "-------------------------\n");
        getBmpInfo(scaleBmp);
        Log.d("my", "--------------------------\n");
//        getBmpInfo(alpha);
//        Log.d("my", "----------------------------\n");
//        getBmpInfo(copy);
//        Log.d("my", "---------------------------\n");
//        getBmpInfo(drawable);

    }

    private void getBmpInfo(Bitmap bmp)
    {
        // 得到图片的长宽
        Log.d("my", "getWidth = " + bmp.getWidth());
        Log.d("my", "getHeight = " + bmp.getHeight());
        // 得到图片的位信息
        Log.d("my", "getConfig = " + bmp.getConfig());
        // 获取当前设备的密度
        Log.d("my", "getDensity = " + bmp.getDensity());
        // 得到某个位置的像素
        Log.d("my", "getPixel = " + String.format("%x", bmp.getPixel(w / 2, h/  2)));
        // 是否具有灰度
        Log.d("my", "hasAlpha = " + bmp.hasAlpha());
        // 是否开启MipMap技术加速
        Log.d("my", "hasMipMap = " + bmp.hasMipMap());
        // 图像是否可以修改
        Log.d("my", "isMutable = " + bmp.isMutable());
        // 是否已经回收
        Log.d("my", "isRecycled = " + bmp.isRecycled());
        // 字节数
        Log.d("my", "getByteCount = " + bmp.getByteCount());
        Log.d("my", "getAllocationByteCount = " + bmp.getAllocationByteCount());
        // 得到宽字节数，一个像素有4个字节，分别为ARGB
        Log.d("my", "getRowBytes = " + bmp.getRowBytes());
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(raw_bmp, 0, 0, paint);

        canvas.drawBitmap(scaleBmp, 50, 50, paint);
//
//        canvas.drawBitmap(copy, 50, 50, paint);
//
//        canvas.drawBitmap(alpha, 100, 100, paint);
//
//        canvas.drawBitmap(drawable, 150, 150, paint);
    }
}
