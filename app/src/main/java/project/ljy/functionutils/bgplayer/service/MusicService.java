package project.ljy.functionutils.bgplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

//// TODO: 2016/12/14 根据生命周期修改调用方法
public class MusicService extends Service {

    private MediaPlayer mMediaPlayer = null;

    private PlayerBinder mBinder = new PlayerBinder();
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    public class PlayerBinder extends Binder {
        public void play() {
            try {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(getAssets().openFd("jwq_syst.mp3").getFileDescriptor());
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                Toast.makeText(MusicService.this, "Music play", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void pause() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                Toast.makeText(MusicService.this, "Music pause", Toast.LENGTH_SHORT).show();
            }
        }

        public void resume() {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
                Toast.makeText(MusicService.this, "Music resume", Toast.LENGTH_SHORT).show();
            }
        }

        public void stop() {
            mMediaPlayer.stop();
            Toast.makeText(MusicService.this, "Music stop", Toast.LENGTH_SHORT).show();
        }

        public void release() {
            mMediaPlayer.release();
        }
    }



}
