package project.ljy.functionutils;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebBrowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_brower);
        WebView webView = (WebView) findViewById(R.id.webview);

        if (webView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
            webView.loadUrl("file:///android_asset/testf.html");
            webView.setWebChromeClient(new WebChromeClient());
            webView.addJavascriptInterface(new TestFunction(),"testFunc");
            // 设置支持javascript
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setBlockNetworkImage(false);
//启动缓存
            webView.getSettings().setAppCacheEnabled(true);
            //设置缓存模式
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
    }
}
