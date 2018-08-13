package tl.com.testmaterialdesign.navigation31.transition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.MainActivity;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2018/8/6.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class ActivityTransition extends BaseActivity {

    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranisition);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll)
    public void onViewClicked() {

        // 返回值MainActivity 他是单例的
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, R.anim.activity_tran_out);

    }
}
