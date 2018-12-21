package tl.com.testmaterialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import tl.com.testmaterialdesign.base.ActivityList;
import tl.com.testmaterialdesign.navigation01.Fragment01;
import tl.com.testmaterialdesign.navigation11.Fragment11;
import tl.com.testmaterialdesign.navigation21.Fragment21;
import tl.com.testmaterialdesign.navigation22.Fragment22;
import tl.com.testmaterialdesign.navigation31.Fragment31;
import tl.com.testmaterialdesign.navigation41.Fragment41;
import tl.com.testmaterialdesign.navigation42.Fragment42;
import tl.com.testmaterialdesign.navigation51.Fragment51;
import tl.com.testmaterialdesign.navigation61.Fragment61;
import tl.com.testmaterialdesign.navigation71.Fragment71;
import tl.com.testmaterialdesign.navigation72.Fragment72;
import tl.com.testmaterialdesign.navigation81.Fragment81;
import tl.com.testmaterialdesign.navigation91.Fragment91;
import tl.com.testmaterialdesign.navigationG.FragmentG;
import tl.com.testmaterialdesign.utils.nullcheck.NullCheckUtils;
import tl.com.testmaterialdesign.utils.toast.SnackbarUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    Toolbar toolbar;

    NavigationView navigationView;

    DrawerLayout drawerLayout;

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        initView();

        ActivityList.add(this);

        test();
    }

    private void test()
    {
        NullCheckUtils.isEmpty(null);
    }

    public void doSomething() {
        flag++;
        Log.d("my", "doSomething flag = " + flag);
        doUiThing();
    }

    public void doUiThing() {
        getSupportActionBar().setTitle("MainActivity" + flag);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
//        doUiThing();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ActivityList.clear();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
            switch (item.getItemId())
            {
                case R.id.menu_01:

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment01())
                            .commit();

                    break;

                case R.id.menu_11:

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment11())
                            .commit();
                    break;

                case R.id.menu_21:

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment21())
                            .commit();
                    break;

                case R.id.menu_22:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment22())
                            .commit();
                    break;

                case R.id.menu_31:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment31())
                            .commit();
                    break;

                case R.id.menu_41:

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment41())
                            .commit();
                    break;
                case R.id.menu_42:

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment42())
                            .commit();
                    break;
                case R.id.menu_51:

                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment51())
                            .commit();
                    break;

                case R.id.menu_61:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment61())
                            .commit();
                    break;

                case R.id.menu_71:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment71())
                            .commit();
                    break;

                case R.id.menu_72:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment72())
                            .commit();
                    break;

                case R.id.menu_81:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment81())
                            .commit();
                    break;

                case R.id.menu_91:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new Fragment91())
                            .commit();
                    break;

                case R.id.menu_G:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, new FragmentG())
                            .commit();
                    break;
            }

            drawerLayout.closeDrawer(Gravity.START);
            return true;
    }

    private void initView()
    {

        // 为toolbar设置标题
        toolbar.setTitle("main");

        // 使用自己的toolbar，作为系统菜单，系统原本的ActionBar被隐藏了，我们可以将
        // getMenuInflater().inflate(R.menu.main, menu)得到的菜单设置到我们的toolbar上
        setSupportActionBar(toolbar);

       // 为drawerLayout添加监听器,并且该监听器会为toolbar添加一个打开navigationView菜单的button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_1:

                final SearchView searchView1 = (SearchView) item.getActionView();
                final SearchView searchView2 = (SearchView) MenuItemCompat.getActionView(item);
                SnackbarUtils.show(MainActivity.this, "bool = " + (searchView1 == searchView2));

                break;

            case R.id.action_3:

                finish();

                break;
        }
        return true;
    }

}
