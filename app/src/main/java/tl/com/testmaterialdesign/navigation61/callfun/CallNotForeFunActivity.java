package tl.com.testmaterialdesign.navigation61.callfun;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.MainActivity;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.ActivityList;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2018/2/12.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class CallNotForeFunActivity extends BaseActivity
{
    @BindView(R.id.bt_call)
    Button btCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_call_not_fore_fun);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView()
    {

    }

    @OnClick(R.id.bt_call)
    public void onViewClicked()
    {
        for (Activity activity : ActivityList.getActivities())
        {
            if (activity instanceof MainActivity)
            {
                MainActivity mainActivity = (MainActivity) activity;

                // 不在前台的应用是会调用的，包括更新UI
                mainActivity.doSomething();
            }
        }
    }
}
