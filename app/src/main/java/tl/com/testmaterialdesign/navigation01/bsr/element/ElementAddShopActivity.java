package tl.com.testmaterialdesign.navigation01.bsr.element;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;
import tl.com.testmaterialdesign.base.BaseVo;
import tl.com.testmaterialdesign.utils.display.PixsUtils;
import tl.com.testmaterialdesign.utils.display.ScreenUtils;

/**
 * Created by tianlin on 2018/2/8.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class ElementAddShopActivity extends BaseActivity implements BeiSaiErAdapter.OnItemClickListener
{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_end)
    ImageView ivEnd;
    @BindView(R.id.bt_beisaier)
    Button btBeisaier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_sai_er_element);
        ButterKnife.bind(this);
        initView();
    }


    class MyTypeEvaluator implements TypeEvaluator<Point>
    {
        private Point controlPoint;

        public MyTypeEvaluator(Point controlPoint) {
            this.controlPoint = controlPoint;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue)
        {
            int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controlPoint.x + t * t * endValue.x);
            int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controlPoint.y + t * t * endValue.y);
            return new Point(x, y);
        }
    }
    @Override
    public void onItemClick(Point start)
    {
        Log.d("my", "状态栏 = " + ScreenUtils.getStateHeight(this) + ", 标题栏 = " + ScreenUtils.getTitleHeight(this));

        Log.d("my", "---start---" + start.toString());

        int endPositions[] = new int[2];
        ivEnd.getLocationOnScreen(endPositions);
        Point end = new Point();
        end.x = endPositions[0];
        end.y = endPositions[1];

        Log.d("my", "---end---" + end.toString());

        int pointX = (start.x + end.x) / 2;
        int pointY = (start.y - PixsUtils.dp2px(this, 150));

        final Point controlPoint = new Point(pointX, pointY);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(controlPoint), start, end);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                Point position = (Point) animation.getAnimatedValue();

                Log.d("my", "onAnimationUpdate position = " + position.toString());
                btBeisaier.setX(position.x);
                btBeisaier.setY(position.y);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {

            }
            @Override
            public void onAnimationCancel(Animator animation)
            {

            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    @Override
    public void initView()
    {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<BaseVo> baseVos = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            baseVos.add(new BeiSaierVo("name" + i));
        }

        BeiSaiErAdapter beiSaiErAdapter = new BeiSaiErAdapter(baseVos, this);
        beiSaiErAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(beiSaiErAdapter);

    }
}
