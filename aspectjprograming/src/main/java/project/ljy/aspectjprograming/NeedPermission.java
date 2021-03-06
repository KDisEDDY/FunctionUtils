package project.ljy.aspectjprograming;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: NeedPermission
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2017/9/29
 * Version: 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface NeedPermission {

    /**
     * 你所申请的权限列表，例如 {@link android.Manifest.permission#READ_CONTACTS}
     * @return 权限列表
     * @see android.Manifest.permission
     */
    String[] permissions() default "";

    /**
     * 合理性解释内容
     * @return 合理性解释内容
     */
    String rationalMessage() default "";

    /**
     * 合理性解释文本资源ID
     * @return
     */
    int rationalMsgResId() default 0;


    /**
     * 合理性解释按钮文本
     * @return 合理性解释按钮文本
     */
    String rationalButton() default "";

    /**
     * 合理性解释按钮文本资源ID
     * @return
     */
    int rationalBtnResId() default 0;

    /**
     * 权限禁止文本内容
     * @return 权限禁止文本内容
     */
    String deniedMessage() default "";

    /**
     * 权限禁止文本资源ID
     * @return
     */
    int deniedMsgResId() default 0;

    /**
     * 权限禁止按钮文本
     * @return 权限禁止按钮文本
     */
    String deniedButton() default "";

    /**
     * 权限禁止按钮文本资源ID
     * @return
     */
    int deniedBtnResId() default 0;

    /**
     * app设置按钮文本
     * @return
     */
    String settingText() default "";

    /**
     * app设置按钮文本资源ID
     * @return
     */
    int settingResId() default 0;

    /**
     * 是否显示跳转到应用权限设置界面
     * @return 是否显示跳转到应用权限设置界面
     */
    boolean needGotoSetting() default false;

    /**
     * 是否无视权限，程序正常往下走
     * @return 是否无视权限，程序正常往下走
     */
    boolean runIgnorePermission() default false;
}
