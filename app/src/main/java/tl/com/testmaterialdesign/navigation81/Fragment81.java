package tl.com.testmaterialdesign.navigation81;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.database.realm.DiffProgressReadAndWrite;
import tl.com.testmaterialdesign.database.realm.DiffThreadReadAndWrite;
import tl.com.testmaterialdesign.navigation81.simplelabel.SimpleLabelActivity;
import tl.com.testmaterialdesign.navigation81.vue.VusJsActivity;

/**
 * Created by tianlin on 2017/7/17.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class Fragment81 extends Fragment
{
    @BindView(R.id.bt_simple_view)
    Button btSimpleView;
    Unbinder unbinder;
    @BindView(R.id.bt_vue_js)
    Button btVueJs;
    @BindView(R.id.main_thread_save)
    Button mainThreadSave;
    @BindView(R.id.io_thread_read)
    Button ioThreadRead;
    @BindView(R.id.test_not_copy)
    Button testNotCopy;
    @BindView(R.id.read_pro_data)
    Button readProData;
    @BindView(R.id.io_thread_read_once)
    Button ioThreadReadOnce;
    @BindView(R.id.test_not_copy2)
    Button testNotCopy2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment81, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_simple_view)
    public void bt_simple_view()
    {
        Intent intent = new Intent(getActivity(), SimpleLabelActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.bt_vue_js)
    public void bt_vue_js()
    {
        Intent intent = new Intent(getActivity(), VusJsActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.read_pro_data)
    public void onViewClicked()
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DiffProgressReadAndWrite> database = realm.where(DiffProgressReadAndWrite.class).findAll();

        List<DiffProgressReadAndWrite> copy = realm.copyFromRealm(database);

        Log.d("my", "read_pro_data copy = " + copy.toString());
    }

    @OnClick(R.id.io_thread_read_once)
    public void io_thread_read_once()
    {
        Realm realm = Realm.getDefaultInstance();

        RealmResults results = realm.where(DiffThreadReadAndWrite.class).findAll();

        List<DiffThreadReadAndWrite> copy = realm.copyFromRealm(results);

        Log.d("my", "copy = " + copy.toString());
    }

    @OnClick(R.id.test_not_copy2)
    public void test_not_copy2()
    {

        new Thread(){
            @Override
            public void run()
            {
                Realm realm = Realm.getDefaultInstance();

                final DiffThreadReadAndWrite diffThreadReadAndWrite = realm.where(DiffThreadReadAndWrite.class).findAll().first();

                Log.d("my", "diffThreadReadAndWrite valid = " + diffThreadReadAndWrite.isValid());

                realm.executeTransaction(new Realm.Transaction()
                {
                    @Override
                    public void execute(Realm realm)
                    {
                        realm = null;

                        SystemClock.sleep(8000);

                        diffThreadReadAndWrite.setName("newName");
                    }
                });

                // 此时发现diffThreadReadAndWrite对象还是有效的
                Log.d("my", "diffThreadReadAndWrite valid = " + diffThreadReadAndWrite.isValid());
            }
        }.start();
    }

    @OnClick(R.id.test_not_copy)
    public void test_not_copy()
    {
        Realm realm = Realm.getDefaultInstance();

        DiffThreadReadAndWrite diffThreadReadAndWrite = realm.where(DiffThreadReadAndWrite.class).findAll().first();

        Log.d("my", "diffThreadReadAndWrite valid = " + diffThreadReadAndWrite.isValid());

        realm.close();

        Log.d("my", "diffThreadReadAndWrite valid = " + diffThreadReadAndWrite.isValid());
    }

    @OnClick(R.id.io_thread_read)
    public void io_thread_read()
    {
        new Thread()
        {
            @Override
            public void run()
            {

                // 一个存在很久的realm实例
                Realm realm = Realm.getDefaultInstance();

                while (true)
                {

                    // 刷新一下数据流，让主线程新添加的数据，子线程能获取到
                    realm.refresh();

                    RealmResults results = realm.where(DiffThreadReadAndWrite.class).findAll();

                    List<DiffThreadReadAndWrite> copy = realm.copyFromRealm(results);

                    Log.d("my", "copy = " + copy.toString());

                    SystemClock.sleep(5000);
                }

            }
        }.start();
    }

    @OnClick(R.id.main_thread_save)
    public void main_thread_save()
    {
        Realm realm = Realm.getDefaultInstance();
        try
        {
            realm.executeTransaction(new Realm.Transaction()
            {
                @Override
                public void execute(Realm realm)
                {
                    DiffThreadReadAndWrite diffThreadReadAndWrite = new DiffThreadReadAndWrite();
                    diffThreadReadAndWrite.setId("1");
                    diffThreadReadAndWrite.setName("a");

                    realm.copyToRealm(diffThreadReadAndWrite);

                }
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (realm != null && !realm.isClosed())
                realm.close();
        }
    }

}
