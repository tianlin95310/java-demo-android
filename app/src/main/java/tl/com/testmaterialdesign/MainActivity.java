package tl.com.testmaterialdesign;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    NavigationView navigationView;

    DrawerLayout drawerLayout;

    private int flag = 0;

    private ViewGroup viewGroup;
    private Bitmap bitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        initView();

        ActivityList.add(this);

        test();
    }

    private void test() {
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
    protected void onResume() {
        super.onResume();
//        doUiThing();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityList.clear();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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

    private void initView() {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    @Override
    protected void onStop() {
        viewGroup = findViewById(android.R.id.content);
        viewGroup.setDrawingCacheEnabled(true);
        bitmap = viewGroup.getDrawingCache(true);

        Bitmap b = blurBitmap(bitmap, 20);
        imageView = new ImageView(MainActivity.this);
        imageView.setImageBitmap(b);
        viewGroup.addView(imageView);
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (imageView != null) {
            viewGroup.removeView(imageView);
        }
    }

    public Bitmap blurBitmap(Bitmap bitmap, float radius) {
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(getApplicationContext());

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(radius);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
//        bitmap.recycle();

//        After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;

    }
}
