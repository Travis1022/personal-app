package com.mycamera.activity;


import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;

import com.mycamera.R;
import com.mycamera.camera.CameraInterface;
import com.mycamera.camera.preview.CameraSurfaceView;
import com.mycamera.ui.MaskView;
import com.mycamera.util.DisplayUtil;

public class CameraActivity extends Activity implements CameraInterface.CamOpenOverCallback {
    private static final String TAG = "CameraActivity";
    CameraSurfaceView surfaceView = null;
    ImageButton shutterBtn;
    MaskView maskView = null;
    float previewRate = -1f;
//    //经过调试，已经确定适配得到 640* 480的分辨率   (1280  *  960)
//    int DST_CENTER_RECT_WIDTH = 213;    //dip
//    int DST_CENTER_RECT_HEIGHT = 160;

//    //经过调试，已经确定适配得到 640* 480的分辨率   (1024  *  768)
//    int DST_CENTER_RECT_WIDTH = 267;    //dip
//    int DST_CENTER_RECT_HEIGHT = 200;

    //经过调试，已经确定适配得到 600* 480的分辨率   (800  *  600)
    int DST_CENTER_RECT_WIDTH = 320;    //dip
    int DST_CENTER_RECT_HEIGHT = 256;



    //                    //px
    //int width = 160;
    //int height = 120;
    Point rectPictureSize = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //开启相机
        Thread openThread = new Thread() {
            @Override
            public void run() {
                CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
            }
        };
        openThread.start();
        setContentView(R.layout.activity_camera);
        //初始化UI
        initUI();
        //初始化View参数
        initViewParams();
        //拍照
        shutterBtn.setOnClickListener(new BtnListeners());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.camera, menu);
        return true;
    }

    private void initUI() {
        surfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);
        shutterBtn = (ImageButton) findViewById(R.id.btn_shutter);
        maskView = (MaskView) findViewById(R.id.view_mask);
    }

    private void initViewParams() {
        LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        Log.i(TAG, "screen: w = " + p.x + " y = " + p.y);
        previewRate = DisplayUtil.getScreenRate(this);
        surfaceView.setLayoutParams(params);

        LayoutParams p2 = shutterBtn.getLayoutParams();
        p2.width = DisplayUtil.dip2px(this, 80);
        p2.height = DisplayUtil.dip2px(this, 80);
        shutterBtn.setLayoutParams(p2);

    }

    @Override
    public void cameraHasOpened() {
        SurfaceHolder holder = surfaceView.getSurfaceHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
        if (maskView != null) {
            Log.e(TAG, "width:" + DisplayUtil.dip2px(this, DST_CENTER_RECT_WIDTH));
            Log.e(TAG, "height:" + DisplayUtil.dip2px(this, DST_CENTER_RECT_HEIGHT));
            Rect screenCenterRect = createCenterScreenRect(DisplayUtil.dip2px(this, DST_CENTER_RECT_WIDTH)
                    , DisplayUtil.dip2px(this, DST_CENTER_RECT_HEIGHT));
            maskView.setCenterRect(screenCenterRect);
        }
    }

    private class BtnListeners implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_shutter:
                    if (rectPictureSize == null) {
                        rectPictureSize = createCenterPictureRect(DisplayUtil.dip2px(CameraActivity.this, DST_CENTER_RECT_WIDTH)
                                , DisplayUtil.dip2px(CameraActivity.this, DST_CENTER_RECT_HEIGHT));
//                        rectPictureSize = createCenterPictureRect(width, height);
                    }
                    CameraInterface.getInstance().doTakePicture(rectPictureSize.x, rectPictureSize.y);
                    break;
                default:
                    break;
            }
        }

    }

    private Point createCenterPictureRect(int w, int h) {

        int wScreen = DisplayUtil.getScreenMetrics(this).x;
        int hScreen = DisplayUtil.getScreenMetrics(this).y;
        int wSavePicture = CameraInterface.getInstance().doGetPictureSize().y;
        int hSavePicture = CameraInterface.getInstance().doGetPictureSize().x;
        float wRate = (float) (wSavePicture) / (float) (wScreen);
        float hRate = (float) (hSavePicture) / (float) (hScreen);

        int wRectPicture = (int) (w * wRate);
        int hRectPicture = (int) (h * hRate);
        return new Point(wRectPicture, hRectPicture);

    }

    /**
     * 绘制有效区域
     *
     * @param w
     * @param h
     * @return
     */
    private Rect createCenterScreenRect(int w, int h) {
        int x1 = DisplayUtil.getScreenMetrics(this).x / 2 - w / 2;
        Log.i("screen width:", "" + DisplayUtil.getScreenMetrics(this).x);
        int y1 = DisplayUtil.getScreenMetrics(this).y / 2 - h / 2;
        Log.i("screen height:", "" + DisplayUtil.getScreenMetrics(this).y);
        int x2 = x1 + w;
        int y2 = y1 + h;
        return new Rect(x1, y1, x2, y2);
    }

}
