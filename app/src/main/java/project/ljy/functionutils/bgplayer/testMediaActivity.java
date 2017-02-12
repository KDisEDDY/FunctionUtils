package project.ljy.functionutils.bgplayer;

/**
 * Title: TestMediaActivity
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/12/15
 * Version: 1.0
 */

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import project.ljy.functionutils.R;
import project.ljy.functionutils.bgplayer.service.MusicService;

public class TestMediaActivity extends AppCompatActivity implements View.OnClickListener{

    private MusicService.PlayerBinder mPlayerBinder = null;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayerBinder = (MusicService.PlayerBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_media);

        Intent bgMusicIntent = new Intent(this, MusicService.class);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_resume).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);

        bindService(bgMusicIntent,mServiceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                mPlayerBinder.play();
                break;
            case R.id.btn_stop:
                mPlayerBinder.stop();
                break;
            case R.id.btn_resume:
                mPlayerBinder.resume();
                break;
            case R.id.btn_pause:
                mPlayerBinder.pause();
                break;
        }
    }
}

