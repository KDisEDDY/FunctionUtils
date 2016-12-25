package project.ljy.functionutils.permission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import project.ljy.functionutils.R;
import utils.PermissionUtils;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener{

    private final static int REQUESTCODE_OPENSMS = 100;

    DoResultListener doResultListener = new DoResultListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.btn_main_opensms).setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       PermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults,doResultListener );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_opensms:
                PermissionUtils.checkAndRequestPermission(this,REQUESTCODE_OPENSMS, Manifest.permission.SEND_SMS);
                break;
            default:
                break;
        }
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
