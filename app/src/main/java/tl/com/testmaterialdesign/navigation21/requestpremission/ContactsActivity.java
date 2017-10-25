package tl.com.testmaterialdesign.navigation21.requestpremission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.contentprovider.TLContactsUtils;
import tl.com.testmaterialdesign.vo.ContactVo;

/**
 * Created by tianlin on 2017/7/20.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class ContactsActivity extends BaseActivity
{

    @BindView(R.id.bt_request)
    Button btRequest;
    @BindView(R.id.rv_contacts)
    RecyclerView rvContacts;
    @BindView(R.id.f4_ll)
    LinearLayout f4Ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_premission);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        List<ContactVo> list = TLContactsUtils.getAllContact(this);

        MyAdapter adapter = new MyAdapter(list, this);
        rvContacts.setAdapter(adapter);
    }

    @OnClick(R.id.bt_request)
    public void onViewClicked()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        Toast.makeText(this, "result = " + result, Toast.LENGTH_SHORT).show();
        if (!(result == PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        Toast.makeText(this, "requestCode" + requestCode, Toast.LENGTH_SHORT).show();
        for (String str : permissions)
        {
            Toast.makeText(this, "str = " + str, Toast.LENGTH_SHORT).show();
        }

        for (int i : grantResults)
        {
            Toast.makeText(this, "str = " + i, Toast.LENGTH_SHORT).show();
        }
    }
}
