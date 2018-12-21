package tl.com.testmaterialdesign.navigationG;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import tl.com.testmaterialdesign.navigationG.jinzita.JinZiTaPukeActivity;

/**
 * Created by tianlin on 2018/11/26.
 * Tel : 15071485690
 * QQ : 953108373
 */
public class FragmentG extends Fragment {
    @BindView(R.id.jinzita)
    Button jinzita;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_g, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.jinzita)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), JinZiTaPukeActivity.class);
        startActivity(intent);
    }
}
