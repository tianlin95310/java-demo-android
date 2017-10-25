package tl.com.testmaterialdesign.navigation81.vue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import tl.com.testmaterialdesign.R;
import tl.com.testmaterialdesign.base.BaseActivity;

/**
 * Created by tianlin on 2017/7/21.
 * Tel : 15071485690
 * QQ : 953108373
 * Function :
 */

public class VusJsActivity extends BaseActivity
{

    private static final String BASE_URL = "http://10.208.61.124:8080/006_vue.js/";

    private static final String PAGE_URL = BASE_URL + "001_helloword.jsp";

    @BindView(R.id.wv_vue_js)
    WebView wvVueJs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_js);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView()
    {
        wvVueJs.getSettings().setJavaScriptEnabled(true);
        wvVueJs.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result)
            {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result)
            {
                return super.onJsConfirm(view, url, message, result);
            }
        });
        wvVueJs.setWebViewClient(new WebViewClient()
        {
        });

        wvVueJs.loadUrl(PAGE_URL);
    }
}
