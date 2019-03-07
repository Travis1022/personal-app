package hzcec.com.thridlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;


/**
 * 常用的第三方库使用以及源码分析
 * EventBus
 * create by txw on 2018-06-23
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.btn_event_bus, R.id.btn_rx_java})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //事件总线
            case R.id.btn_event_bus:
                break;
            //响应式编程
            case R.id.btn_rx_java:
                break;
        }
    }


    @Subscribe(ThreadMode=ThreadMode.MAIN){

    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
