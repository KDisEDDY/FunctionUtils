package project.ljy.functionutils.permission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import project.ljy.functionutils.R;
import utils.PermissionUtils;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener{

    private final static int REQUESTCODE_OPENSMS = 100;

    DoResultListener doResultListener = new DoResultListener();
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // Successfully.
            if(requestCode == REQUESTCODE_OPENSMS) {
                Uri smsToUri = Uri.parse("smsto:");
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                intent.putExtra("sms_body","ddddddddddd");
                startActivity(intent);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // Failure.
            if(requestCode == REQUESTCODE_OPENSMS) {
                Toast.makeText(PermissionActivity.this, "Permisstion denied", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private RationaleListener rationaleListener = (requestCode, rationale) -> AlertDialog.newBuilder(this)
            .setTitle("Tips")
            .setMessage("需要提供读取短信权限用于内容收集")
            .setPositiveButton("好的", (dialog, which) -> rationale.resume())
            .setNegativeButton("拒绝", (dialog, which) -> rationale.cancel()).show();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.btn_main_opensms).setOnClickListener(this);
        findViewById(R.id.btn_opensms_lib).setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       PermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults,doResultListener );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_opensms:
                if(PermissionUtils.hasPermissions(this,Manifest.permission.SEND_SMS,Manifest.permission.READ_CALENDAR)){
                    Uri smsToUri = Uri.parse("smsto:");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                    intent.putExtra("sms_body","ddddddddddd");
                    startActivity(intent);
                } else {
                    PermissionUtils.checkAndRequestPermission(this,REQUESTCODE_OPENSMS, Manifest.permission.SEND_SMS,Manifest.permission.READ_CALENDAR);
                }
                break;
            case R.id.btn_opensms_lib:
                AndPermission.with(this)
                        .requestCode(REQUESTCODE_OPENSMS)
                        .permission(Permission.SMS)
                        .rationale(rationaleListener)
                        .callback(listener)
                        .start();
                break;
            default:
                break;
        }
    }

    private class DoResultListener implements PermissionUtils.ResultListener{

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
