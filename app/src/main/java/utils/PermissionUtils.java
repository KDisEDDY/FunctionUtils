package utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
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
@TargetApi(23)
public class PermissionUtils {

    private ResultListener resultListener = null;

    public static  void checkAndRequestPermission(Context context,int requestCode,Permissions... permissionsGroup){

        for(Permissions p : permissionsGroup){
            int code = ActivityCompat.checkSelfPermission(context,p.toString());
            if (code == PackageManager.PERMISSION_GRANTED) {
            }
            else{
                ActivityCompat.requestPermissions((Activity) context,new String[]{p.toString()},requestCode);
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
