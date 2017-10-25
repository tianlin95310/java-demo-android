package tl.com.testmaterialdesign.navigation01.searchview.nestedview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/3/15.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class NestedActivity extends AppCompatActivity
{
    Toolbar toolbar;

    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        textView = (TextView) findViewById(R.id.nested_tv);

        initView();
    }

    private void initView()
    {
        String key = getIntent().getStringExtra("key");
        textView.setText(key);

        toolbar.setTitle("nestedview");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
