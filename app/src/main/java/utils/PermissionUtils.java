package utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Title: PermissionUtils
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/7/18
 * Version: 1.0
 */
public class PermissionUtils {


    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context
     *         the calling context.
     * @param perms
     *         one ore more permissions, such as {@code android.Manifest.permission.CAMERA}.
     * @return true if all permissions are already granted, false if at least one permission is not yet granted.
     */
    public static boolean hasPermissions(@NonNull Context context, @NonNull String... perms) {
        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm) ==
                    PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public static  void checkAndRequestPermission(Context context,int requestCode,String... permissionsGroup){

        for(String p : permissionsGroup){
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {

                    // 判断是否提示用户获取权限行为的解释，为true时向用户解释
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,p)) {

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{p},
                                requestCode);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                Toast.makeText(context, "grant the permission",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public static void  onRequestPermissionsResult(Context context,int requestCode, String[] permissions, int[] grantResults,ResultListener resultListener) {

        for(int i = 0 ; i < grantResults.length ; i++){
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED){                                                                         //do something
                resultListener.onFunction(context,requestCode);
            }
            else if(!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[i])){                                // show tips  go to set
                Toast.makeText(context, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface ResultListener{
        void onFunction(Context context,int requestCode);
    }

}
