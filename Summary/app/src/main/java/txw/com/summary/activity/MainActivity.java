package txw.com.summary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import txw.com.summary.R;

/**
 * Created by txw on 2018/7/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_jni)
    Button mBtnJni;        //JNI调用功能

    @BindView(R.id.btn_info)
    Button mBtnInfo;       //基础信息上传功能

    @BindView(R.id.btn_photo)
    Button mBtnPhoto;      //照片文件上传功能

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_jni, R.id.btn_info, R.id.btn_photo})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jni:
                Intent jniIntent = new Intent(this, JniActivity.class);
                startActivity(jniIntent);
                break;
            case R.id.btn_info:
                Intent infoIntent = new Intent(this, InfoActivity.class);
                startActivity(infoIntent);
                break;
            case R.id.btn_photo:
                Intent cameraIntent = new Intent(this, CameraActivity.class);
                startActivity(cameraIntent);
                break;
        }
    }
}
