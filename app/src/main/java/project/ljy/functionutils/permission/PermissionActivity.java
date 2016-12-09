package project.ljy.functionutils.permission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project.ljy.functionutils.R;
import utils.PermissionUtils;
import utils.Permissions;

public class PermissionActivity extends AppCompatActivity {

    private final static int REQUESTCODE_OPENSMS = 100;

    DoResultListener doResultListener = new DoResultListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        PermissionUtils.checkAndRequestPermission(this,REQUESTCODE_OPENSMS, Permissions.send_sms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       PermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults,doResultListener );
    }

    class DoResultListener implements PermissionUtils.ResultListener{

        @Override
        public void onFunction(Context context, int requestCode) {
            switch(requestCode){
                case REQUESTCODE_OPENSMS:
                    Uri smsToUri = Uri.parse("smsto:");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                    intent.putExtra("sms_body","ddddddddddd");
                    startActivity(intent);
                    break;
                default:break;
            }
        }
    }
}
