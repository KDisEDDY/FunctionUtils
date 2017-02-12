package project.ljy.functionutils;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import project.ljy.functionutils.bgplayer.TestMediaActivity;
import project.ljy.functionutils.fragmenttest.MyFragmentActivity;
import project.ljy.functionutils.listpopup.ListPopupWindowActivity;
import project.ljy.functionutils.notification.NotificationDownLoadActivity;
import project.ljy.functionutils.permission.PermissionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("activityState" , "----------------------------onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("activityState" , "----------------------------onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("activityState" , "----------------------------onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("activityState" , "----------------------------onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("activityState" , "----------------------------onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("activityState" , "----------------------------onStop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("activityState" , "----------------------------onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("activityState" , "----------------------------onRestoreInstanceState");
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_main_testMPermission:
                startActivity(new Intent(MainActivity.this,PermissionActivity.class));
                break;
            case R.id.btn_main_testListPopwindow:
                startActivity(new Intent(MainActivity.this,ListPopupWindowActivity.class));
                break;
            case R.id.btn_fragment_test:
                startActivity(new Intent(MainActivity.this,MyFragmentActivity.class));
                break;
            case R.id.btn_notification_download:
                startActivity(new Intent(MainActivity.this,NotificationDownLoadActivity.class));
                break;
            case R.id.btn_background_musicplayer:
                startActivity(new Intent(MainActivity.this, TestMediaActivity.class));
                break;
            case R.id.btn_webView_test:
                startActivity(new Intent(MainActivity.this, WebBrowerActivity.class));
                break;
            default:
                break;
        }
    }
}
