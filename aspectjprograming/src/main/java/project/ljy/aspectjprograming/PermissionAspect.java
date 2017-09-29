package project.ljy.aspectjprograming;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: PermissionAspect
 * Description: 运行时管理Aspect
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2017/9/29
 * Version: 1.0
 */

@Aspect
public class PermissionAspect {

    NeedPermission needPermission;


    @Pointcut("execution(@project.ljy.aspectjprograming.*.NeedPermission * *(..))")
    public void NeedPermissionFunction(){

    }

    @Before("NeedPermissionFunction")
    private void readAnnoationInfo(JoinPoint joinPoint){
        Class clazz = joinPoint.getSignature().getDeclaringType();
        List<Field> list = Arrays.asList(clazz.getClass().getDeclaredFields());
        for(int i=0;i<list.size();i++) {
            Field field = list.get(i);
            if (field.isAnnotationPresent(NeedPermission.class)) {
                for (Annotation anno : field.getDeclaredAnnotations()) {//获得所有的注解
                    if (anno.annotationType().equals(NeedPermission.class)) {//找到自己的注解
                        String[] permissions = ((NeedPermission)anno).permissions();

                    }
                }
            }
        }
    }
}
