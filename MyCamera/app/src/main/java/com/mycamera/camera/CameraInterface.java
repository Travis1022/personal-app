package com.mycamera.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;

import com.mycamera.util.CamParaUtil;
import com.mycamera.util.FileUtil;
import com.mycamera.util.ImageUtil;

import java.io.IOException;
import java.util.List;

public class CameraInterface {
    private static final String TAG = "CameraInterface";
    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing = false;
    private float mPreviwRate = -1f;
    private static CameraInterface mCameraInterface;

    public interface CamOpenOverCallback {
        public void cameraHasOpened();
    }

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    public void doOpenCamera(CamOpenOverCallback callback) {
        Log.i(TAG, "Camera open....");
        mCamera = Camera.open();
        Log.i(TAG, "Camera open over....");
        callback.cameraHasOpened();
    }

    public void doStartPreview(SurfaceHolder holder, float previewRate) {
        Log.i(TAG, "doStartPreview...");
        if (isPreviewing) {
            mCamera.stopPreview();
            return;
        }
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initCamera(previewRate);
        }


    }

    public void doStartPreview(SurfaceTexture surface, float previewRate) {
        Log.i(TAG, "doStartPreview...");
        if (isPreviewing) {
            mCamera.stopPreview();
            return;
        }
        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(surface);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initCamera(previewRate);
        }

    }


    public void doStopCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mPreviwRate = -1f;
            mCamera.release();
            mCamera = null;
        }
    }

    public void doTakePicture() {
        if (isPreviewing && (mCamera != null)) {
            mCamera.takePicture(mShutterCallback, null, mJpegPictureCallback);
        }
    }

    int DST_RECT_WIDTH, DST_RECT_HEIGHT;

    // 拍照
    public void doTakePicture(int w, int h) {
        if (isPreviewing && (mCamera != null)) {
            Log.i(TAG, "size:width = " + w + " h = " + h);
            DST_RECT_WIDTH = w;
            DST_RECT_HEIGHT = h;
            mCamera.takePicture(mShutterCallback, null, mRectJpegPictureCallback);
        }
    }

    public Point doGetPictureSize() {
        Size s = mCamera.getParameters().getPictureSize();
        return new Point(s.width, s.height);
    }


    private void initCamera(float previewRate) {
        if (mCamera != null) {

            mParams = mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);
//        960*1280      640*480
//            //获取可支持的分辨率
//			//CamParaUtil.getInstance().printSupportPreviewSize(mParams);
//            Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(mParams.getSupportedPictureSizes(), previewRate, 800);
//            mParams.setPictureSize(pictureSize.width, pictureSize.height);
//            Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(mParams.getSupportedPreviewSizes(), previewRate, 800);
//            Log.e("previewSize", "" + previewSize.width + "*" + previewSize.height);
//            mParams.setPreviewSize(previewSize.width, previewSize.height);
//            mCamera.setDisplayOrientation(90);


            //获取可支持的预览分辨率
//            CamParaUtil.getInstance().printSupportPictureSize(mParams);
//            CamParaUtil.getInstance().printSupportPreviewSize(mParams);
            Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(mParams.getSupportedPictureSizes(), previewRate, 800);
            mParams.setPictureSize(800, 600);
            Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(mParams.getSupportedPreviewSizes(), previewRate, 800);
            Log.e("previewSize", "" + previewSize.width + "*" + previewSize.height);
            mParams.setPreviewSize(previewSize.width, previewSize.height);
            mCamera.setDisplayOrientation(90);

//			CamParaUtil.getInstance().printSupportFocusMode(mParams);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if (focusModes.contains("continuous-video")) {
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);
            mCamera.startPreview();


            isPreviewing = true;
            mPreviwRate = previewRate;

            mParams = mCamera.getParameters();
            Log.i(TAG, "PreviewSize--With = " + mParams.getPreviewSize().width
                    + "Height = " + mParams.getPreviewSize().height);
            Log.i(TAG, "PictureSize--With = " + mParams.getPictureSize().width
                    + "Height = " + mParams.getPictureSize().height);
        }
    }


    ShutterCallback mShutterCallback = new ShutterCallback() {
        public void onShutter() {
            // TODO Auto-generated method stub
            Log.i(TAG, "myShutterCallback:onShutter...");
        }
    };
    PictureCallback mRawCallback = new PictureCallback() {

        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            Log.i(TAG, "myRawCallback:onPictureTaken...");

        }
    };

    PictureCallback mJpegPictureCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            Log.i(TAG, "myJpegCallback:onPictureTaken...");
            Bitmap b = null;
            if (null != data) {
                b = BitmapFactory.decodeByteArray(data, 0, data.length);
                mCamera.stopPreview();
                isPreviewing = false;
            }
            if (null != b) {
                Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
                FileUtil.saveBitmap(rotaBitmap);
            }
            mCamera.startPreview();
            isPreviewing = true;
        }
    };


    //拍照之后回调
    PictureCallback mRectJpegPictureCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            Log.i(TAG, "myJpegCallback:onPictureTaken...");
            Bitmap b = null;
            if (null != data) {
                b = BitmapFactory.decodeByteArray(data, 0, data.length);
                mCamera.stopPreview();
                isPreviewing = false;
            }
            if (null != b) {

                Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
                int x = rotaBitmap.getWidth() / 2 - DST_RECT_WIDTH / 2;
                int y = rotaBitmap.getHeight() / 2 - DST_RECT_HEIGHT / 2;
                Log.i(TAG, "rotaBitmap.getWidth() = " + rotaBitmap.getWidth()
                        + " rotaBitmap.getHeight() = " + rotaBitmap.getHeight());
                Bitmap rectBitmap = Bitmap.createBitmap(rotaBitmap, x, y, DST_RECT_WIDTH, DST_RECT_HEIGHT);
                FileUtil.saveBitmap(rectBitmap);
                if (rotaBitmap.isRecycled()) {
                    rotaBitmap.recycle();
                    rotaBitmap = null;
                }
                if (rectBitmap.isRecycled()) {
                    rectBitmap.recycle();
                    rectBitmap = null;
                }
            }
            mCamera.startPreview();
            isPreviewing = true;
            if (!b.isRecycled()) {
                b.recycle();
                b = null;
            }

        }
    };


}
