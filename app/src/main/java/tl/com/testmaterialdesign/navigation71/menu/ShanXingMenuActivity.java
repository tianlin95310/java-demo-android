package tl.com.testmaterialdesign.navigation71.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.utils.thread.ThreadManager;

/**
 * Created by tianlin on 2018/5/24.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class ShanXingMenuActivity extends BaseActivity implements OnMenuListener
{
    @BindView(R.id.sh_menu)
    ShanXingMenuView shMenu;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.ll_bg_layer)
    LinearLayout llBgLayer;
    @BindView(R.id.tv1)
    TextView tv1;

    private Bitmap bitmap;

    @Override
    public void initView()
    {
        Bitmap initIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_message);
        Bitmap openingIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_opening);
        Bitmap menu1Icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_history_record);
        Bitmap menu2Icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_copy);
        Bitmap menu3Icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_peisong);
        shMenu.setInitIcon(initIcon);
        shMenu.setOpeningIcon(openingIcon);
        shMenu.setMenu1Icon(menu1Icon);
        shMenu.setMenu2Icon(menu2Icon);
        shMenu.setMenu3Icon(menu3Icon);

        shMenu.setMenu1Title("历史订单");
        shMenu.setMenu2Title("复制当日订单");
        shMenu.setMenu3Title("未配送订单");
        shMenu.setMessage("8");
        shMenu.setMenu3Message("5");

        shMenu.setOnMenuListener(this);

        nsv.setDrawingCacheEnabled(true);
    }

    @Override
    public void onSelectMenu(int menu)
    {
        response(this, "选择的功能是" + menu);

        llBgLayer.setBackground(null);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled())
        {
            bitmap.recycle();
            bitmap = null;
        }
    }

    @Override
    public void onMenuStatusChange(boolean isOpen)
    {

    }

    @Override
    public void onSlideDistance(float distance, float maxValue, float minValue)
    {
        Log.d("my", "currentTimeMillis = " + System.currentTimeMillis());

        if (distance < maxValue && distance > minValue)
        {

            float k = (maxValue - minValue) / 25.0f;
            float b = minValue;
            final float radius = (distance - b) / k;

            bitmap = nsv.getDrawingCache(true);

            ThreadManager.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    if (bitmap != null && !bitmap.isRecycled())
                    {
                        Bitmap blur = blurBitmap(bitmap, radius);
                        refresh(blur);
                    }
                }
            });
        }
    }

    private void refresh(final Bitmap blur)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (!blur.isRecycled())
                {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), blur);
                    llBgLayer.setBackground(bitmapDrawable);
                }
            }
        });
    }

    public Bitmap blurBitmap(Bitmap bitmap, float radius)
    {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        RenderScript rs = RenderScript.create(getApplicationContext());

        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(radius);

        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        allOut.copyTo(outBitmap);

        //recycle the original bitmap
//        bitmap.recycle();

        rs.destroy();

        return outBitmap;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanxing_menu);
        ButterKnife.bind(this);

        initView();
    }

    @OnClick(R.id.nsv)
    public void onViewClicked()
    {
        shMenu.hide();
    }

    @OnClick(R.id.tv1)
    public void tv1()
    {
        response(this, "tv1我被点击了");
    }
}
