package project.ljy.aspectjprograming;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Title: ActivityAspect
 * Description：activity的Aspect文件
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2017/9/29
 * Version: 1.0
 */
@Aspect
public class ActivityAspect {
    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {

        String key = joinPoint.getSignature().toString();

        Log.d("TAG", "onActivityMethodAfter: " + key);

    }



}
