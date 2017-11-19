package eddy.bitmapandcamerafunction;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Title: CameraPreviewView
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2017/11/14
 * Version: 1.0
 */
public class CameraPreviewView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {
    private static final String TAG = "CameraPreview";

    private Camera mCamera;
    private CameraPreviewCallback mCameraPreviewCallback;

    private int orientation;
    int imageAngle;

    /**
     * 设置屏幕方向
     *
     * @param orientation Configuration.ORIENTATION_LANDSCAPE 或者
     * Configuration.ORIENTATION_PORTRAIT
     */
    public void setScreenOrientation(int orientation) {
        this.orientation = orientation;
    }

    // 摄像头id
    int cameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    private boolean mPreviewing = true;
    private boolean mSurfaceCreated = false;
    Context context;

    /**
     * setReqPrevWH:设置希望的预览分辨率.
     */
    public void setReqPrevWH(int reqPrevW, int reqPrevH) {
        this.reqPrevW = reqPrevW;
        this.reqPrevH = reqPrevH;
    }

    int reqPrevW = 640, reqPrevH = 480;

    public CameraPreviewView(Context context) {
        super(context);
        this.context = context;
    }

    public CameraPreviewView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        this.context = context;
    }

    public CameraPreviewView(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;
    }

    public void setCamera(Camera camera) {
        mCamera = camera;
        if (mCamera != null) {
            getHolder().addCallback(this);
            if (mPreviewing) {
                requestLayout();
            } else {
                showCameraPreview();
            }
        }
    }

    @Override public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceCreated = true;
    }

    @Override public void surfaceChanged(SurfaceHolder surfaceHolder, int a, int b, int c) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        stopCameraPreview();
        showCameraPreview();
    }

    @Override public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mSurfaceCreated = false;
        stopCameraPreview();
    }

    public void showCameraPreview() {
        if (mCamera != null) {
            try {
                mPreviewing = true;
                mCamera.setPreviewDisplay(getHolder());

                mCamera.startPreview();
                mCamera.setPreviewCallback(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setCameraPreviewCallback(CameraPreviewCallback mCameraPreviewCallback) {
        this.mCameraPreviewCallback = mCameraPreviewCallback;
    }

    public void stopCameraPreview() {
        if (mCamera != null) {
            try {

                mPreviewing = false;
                mCamera.cancelAutoFocus();
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void openFlashlight() {
        if (flashLightAvaliable()) {
        }
    }

    public void closeFlashlight() {
        if (flashLightAvaliable()) {
        }
    }

    private boolean flashLightAvaliable() {
        return mCamera != null && mPreviewing && mSurfaceCreated && getContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    /******************************************************************/
    public Camera.Size setPreviewSize() {
        Camera.Parameters parameters = mCamera.getParameters();
        return parameters.getPreviewSize();
    }

    /**
     * 打开摄像头开始预览，但是并未开始识别
     */
    public void StartCamera() {
        if (mCamera != null) {
            return;
        }

        try {
            mCamera = Camera.open(cameraId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCamera(mCamera);
    }

    /**
     * 关闭摄像头预览，并且隐藏扫描框
     */
    public void StopCamera() {
        if (mCamera != null) {
            stopCameraPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override public void onPreviewFrame(byte[] data, Camera camera) {
        Log.e(TAG, "onPreviewFrame: " + data.toString());
        if(mCameraPreviewCallback != null){
            mCameraPreviewCallback.onCameraPreviewFrame(data,camera , cameraId);
        }
        switch (cameraId){
            case Camera.CameraInfo.CAMERA_FACING_FRONT://前置摄像头
                if (Configuration.ORIENTATION_PORTRAIT == orientation){//竖屏

                }
                break;
            case Camera.CameraInfo.CAMERA_FACING_BACK://后置摄像头
                if (Configuration.ORIENTATION_PORTRAIT == orientation){

                }
                break;
            default:break;
        }

    }

    /**
     * 切换摄像头
     */
    public int switchCarema() {
        StopCamera();
        if (cameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        } else {
            cameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }
        StartCamera();
        return cameraId;
    }

    public interface CameraPreviewCallback{
        void onCameraPreviewFrame(byte[] data, Camera camera, int cameraId);
    }

}
