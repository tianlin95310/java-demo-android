package tl.com.testmaterialdesign.navigation71;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.navigation71.autoR.AutoRefreshActivity;
import tl.com.testmaterialdesign.navigation71.check.OnKeyCheckActivity;
import tl.com.testmaterialdesign.navigation71.index.IndexViewActivity;
import tl.com.testmaterialdesign.navigation71.menu.ShanXingMenuActivity;
import tl.com.testmaterialdesign.navigation71.pullrefresh.TLPullRefActivity;
import tl.com.testmaterialdesign.navigation71.shanxing.ShanXingRatioActivity;
import tl.com.testmaterialdesign.navigation71.shanxingpro.ShanXingProgressActivity;
import tl.com.testmaterialdesign.navigation71.tiaoxingtu.TiaoXingTuActivity;

/**
 * Created by tianlin on 2017/7/7.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class Fragment71 extends Fragment {
    @BindView(R.id.bt1_f702)
    Button bt1F702;
    Unbinder unbinder;
    @BindView(R.id.bt1_f71)
    Button bt1F71;
    @BindView(R.id.bt1_f72)
    Button bt1F72;
    @BindView(R.id.bt1_f73)
    Button bt1F73;
    @BindView(R.id.bt1_f74)
    Button bt1F74;
    @BindView(R.id.bt1_f75)
    Button bt1F75;
    @BindView(R.id.bt1_f76)
    Button bt1F76;
    @BindView(R.id.bt1_f8)
    Button bt1F8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment71, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt1_f702)
    public void bt1_f702() {
        Intent intent = new Intent(getActivity(), OnKeyCheckActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f71)
    public void bt1_f71() {
        Intent intent = new Intent(getActivity(), TLPullRefActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f72)
    public void bt1_f72() {
        Intent intent = new Intent(getActivity(), TiaoXingTuActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f73)
    public void bt1_f73() {
        Intent intent = new Intent(getActivity(), ShanXingRatioActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f74)
    public void bt1_f74() {
        Intent intent = new Intent(getActivity(), ShanXingMenuActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f75)
    public void bt1_f75() {
        Intent intent = new Intent(getActivity(), ShanXingProgressActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f76)
    public void bt1_f76() {
        Intent intent = new Intent(getActivity(), AutoRefreshActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt1_f8)
    public void bt1_f8() {
        Intent intent = new Intent(getActivity(), IndexViewActivity.class);
        startActivity(intent);
    }
}
