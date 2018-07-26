package tl.com.testmaterialdesign.navigation01.bsr.zhanlianbubble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2018/7/23.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class WaveViewActivity extends BaseActivity {

    @BindView(R.id.wv)
    WaveView wv;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.sb_amplitude)
    SeekBar sbAmplitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {

        wv.startAnim();

        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // 0 - 0
                // 100 - hight * 0.9
                // pro - x
                wv.setmWaveHeight(seekBar.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbAmplitude.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wv.setmWaveAmplitude(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
