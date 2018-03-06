package tl.com.testmaterialdesign.navigation61.wallpaper;

import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.support.annotation.Nullable;

import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2018/2/12.
 * Tel : 15071485690
 * QQ : 953108373
 */

public class SetWallpaperActivity extends BaseActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    public void initView()
    {
    }

    class LiveWallpaperService extends WallpaperService {
        @Override
        public Engine onCreateEngine()
        {
            return null;
        }
    }
}
