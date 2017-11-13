package eddy.bitmapandcamerafunction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * ClassName: BitmapActivity
 * function: bitmap的功能测试
 * Created by EDDY
 * CreateTime:2017/11/13
 */
public class BitmapActivity extends AppCompatActivity{

    private final static int REQUEST_CAMERA_THUMBNAIL = 1;          //获取缩略图
    private final static int REQUEST_CAMERA_NORMAL = 2;             //获取原图

    private ImageView mShowImg ;
    private String mDirectory ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        initView();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_request_thumbnail:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
                startActivityForResult(intent, REQUEST_CAMERA_THUMBNAIL);
                break;
            case R.id.btn_request_normal:
                mDirectory = getExternalCacheDir().getAbsolutePath()+ File.separator + new Date().getTime() + ".png";
                if(mDirectory != null){
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
                    Uri photoUri = Uri.fromFile(new File(mDirectory)); // 传递路径
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
                    startActivityForResult(intent2, REQUEST_CAMERA_NORMAL);
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CAMERA_THUMBNAIL){
            Bundle bundle = data.getExtras(); // 从data中取出传递回来缩略图的信息，图片质量差，适合传递小图片
            Bitmap bitmap = (Bitmap) bundle.get("data"); // 将data中的信息流解析为Bitmap类型
            mShowImg.setImageBitmap(bitmap);// 显示图片
        } else if(requestCode == REQUEST_CAMERA_NORMAL){
            //显示正常图片大小
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(mDirectory); // 根据路径获取数据
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                mShowImg.setImageBitmap(bitmap);// 显示图片
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();// 关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView(){
        mShowImg = (ImageView) findViewById(R.id.iv_show);
    }
}
