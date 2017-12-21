package tl.com.testmaterialdesign.navigation61.fullanddialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2017/11/29.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class DialogThemeActivity extends BaseActivity
{
    @BindView(R.id.bt_change)
    Button btChange;

    boolean aBoolean = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_and_dialog);

        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView()
    {

    }

    @OnClick(R.id.bt_change)
    public void bt_change()
    {

    }
}
