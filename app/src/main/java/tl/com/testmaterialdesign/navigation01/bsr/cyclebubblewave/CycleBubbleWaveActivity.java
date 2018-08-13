package tl.com.testmaterialdesign.navigation01.bsr.cyclebubblewave;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2018/7/24.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class CycleBubbleWaveActivity extends BaseActivity {
    @BindView(R.id.cbw)
    CycleBubbleWave cbw;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.sb_amplitude)
    SeekBar sbAmplitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_buble_wave);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {

        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                cbw.setDegree(seekBar.getProgress());

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
                cbw.setmWaveAmplitude(seekBar.getProgress());
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
