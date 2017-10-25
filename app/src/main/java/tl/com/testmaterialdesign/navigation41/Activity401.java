package tl.com.testmaterialdesign.navigation41;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.utils.display.DensityUtils;

/**
 * Created by tianlin on 2017/4/17.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class Activity401 extends AppCompatActivity
{
    @BindView(R.id.iv_man)
    ImageView ivMan;

    float rateX = 1.0f;
    float rateY = 1.0f;

    Matrix matrix;
    @BindView(R.id.f8_scale_in)
    Button f8ScaleIn;
    @BindView(R.id.f8_scale_out)
    Button f8ScaleOut;

    float density = 0;
    int man_w;
    int man_h;

    int bust_w;
    int bust_h;
    @BindView(R.id.iv_man_head)
    ImageView ivManHead;
    @BindView(R.id.iv_bust)
    ImageView ivBust;
    @BindView(R.id.iv_man_waist_hip)
    ImageView ivManWaistHip;
    @BindView(R.id.iv_man_waist_leg)
    ImageView ivManWaistLeg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_matrix_operate);
        ButterKnife.bind(this);
        matrix = ivMan.getMatrix();
        initView();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        man_w = ivMan.getWidth();
        man_h = ivMan.getHeight();

        density = DensityUtils.getScreenDensity(this);

        Bitmap man = ((BitmapDrawable)ivMan.getDrawable()).getBitmap();

        Log.d("my", "getHeight" + man.getHeight());

        Bitmap head = Bitmap.createBitmap(man, 0, 0, man_w, (int) (100 * density));

        Bitmap bust = Bitmap.createBitmap(man, 0, (int)(100 * density), man_w, (int)(80 * density));
        bust_w = bust.getWidth();
        bust_h = bust.getHeight();

        Bitmap waist_hip = Bitmap.createBitmap(man, 0, (int)(180 * density), man_w, (int)(200 * density));

        Bitmap leg = Bitmap.createBitmap(man, 0, (int)(380 * density), man_w, (int)(250 * density));

        ivManHead.setImageBitmap(head);
        ivBust.setImageBitmap(bust);

        ivManWaistHip.setImageBitmap(waist_hip);
        ivManWaistLeg.setImageBitmap(leg);
    }

    private void initView()
    {
    }

    @OnClick({R.id.f8_scale_in, R.id.f8_scale_out})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.f8_scale_in:
                rateX = rateX - 0.01f;
                matrix.setScale(rateX, 1, bust_w / 2, bust_h / 2);
                break;
            case R.id.f8_scale_out:
                rateX = rateX + 0.01f;
                matrix.setScale(rateX, 1, bust_w / 2, bust_h / 2);
                break;
        }

        ivBust.setImageMatrix(matrix);

        Log.d("my", "iv man_w = " + man_w * rateX + ",iv man_h = " + man_h * rateY);
        Log.d("my", "rateX = " + rateX + ",rateY = " + rateY);
    }
}
