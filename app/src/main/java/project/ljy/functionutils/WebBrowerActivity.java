package project.ljy.functionutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebBrowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_brower);
        WebView webView = (WebView) findViewById(R.id.webview);

        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/testf.html.html");
            webView.addJavascriptInterface(new TestFunction(),"testFunc");
        }
    }
}
