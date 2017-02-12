package project.ljy.functionutils;

import android.webkit.JavascriptInterface;

/**
 * Title: TestFunction
 * Description:js与原生交互接口类
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/7/26
 * Version: 1.0
 */
public class TestFunction {
    public TestFunction() {
    }

    @JavascriptInterface
    public int add(int a, int b){
        return a + b;
    }
}
