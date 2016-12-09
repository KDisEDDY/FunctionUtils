package project.ljy.functionutils.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import project.ljy.functionutils.R;

/**
 * 实现在通知栏里面更新下载进度条的功能
 */
public class NotificationDownLoadActivity extends AppCompatActivity {

    private int mNOTIFICATION_ID = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_down_load);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_show_notification:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setSmallIcon(R.mipmap.icon120);
                builder.setContentText("正在下载中。。。");
                // TODO: 2016/12/9 添加通知跳转的activity
                Intent intent = new Intent();
                intent.putExtra("info", "郭嘉给你发了一个计策！");
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);
                //获取Notification
                Notification n = builder.build();
                //通过NotificationCompat.Builder.build()来获得notification对象自己
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //然后调用NotificationManager.notify()向系统转交
                manager.notify(mNOTIFICATION_ID, n);

                break;
            default:
                break;
        }
    }
}
