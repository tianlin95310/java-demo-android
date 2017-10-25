package tl.com.testmaterialdesign.navigation01.liaotian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tl.com.testmaterialdesign.R;

/**
 * Created by tianlin on 2017/3/8.
 * Tel : 15071485692
 * QQ : 953108373
 * Function :
 */

public class LiaotianActivity extends AppCompatActivity
{

    RecyclerView n2_rv;

    EditText et;

    Button bt;

    Toolbar toolbar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liao_tian);

        n2_rv = (RecyclerView) findViewById(R.id.n2_rv);
        et = (EditText) findViewById(R.id.n2_et);
        bt = (Button) findViewById(R.id.n2_send);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);

        initView();

    }

    private void initView()
    {
        // 将我们自定义的toolbar设置到系统的toolbar上
        setSupportActionBar(toolbar2);
        // 不显示toolbar的标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        n2_rv.setLayoutManager( new LinearLayoutManager(this));
        List<LiaoTianVo> list = new ArrayList<>();

        for(int i = 0 ; i < 10; i++)
        {
            list.add(new LiaoTianVo("" + ((i % 2 ) + 1), "content" + i));
        }
        final MyAdapter adapter = new MyAdapter(this, list);

        n2_rv.setAdapter(adapter);

        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String text = et.getText().toString();
                if(TextUtils.isEmpty(text))
                {
                    return;
                }
                Random random = new Random();
                LiaoTianVo liaoTianVo = new LiaoTianVo(random.nextBoolean() ? "1" : "2", text);

                MyAdapter myAdapter = (MyAdapter) n2_rv.getAdapter();

                // 先移动，才通知插入更新
                n2_rv.scrollToPosition(myAdapter.getItemCount());
                myAdapter.add(liaoTianVo);
                myAdapter.notifyItemInserted(myAdapter.getItemCount());

                et.setText("");
            }
        });
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
        Toast.makeText(this, "onOptionsItemSelected " + item.getItemId(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
