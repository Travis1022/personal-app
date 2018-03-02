package hzcec.com.servicedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;


/**
 * create by txw on 2017-11-14
 */
public class FunctionActivity extends AppCompatActivity {

    private MyService.MyBinder mBinder;

    private boolean isServiceBind = false;


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        isServiceBind = true;
        if (mBinder != null) {
            mBinder.test();
        }
    }


    @Override
    protected void onDestroy() {
        if (isServiceBind) {
            unbindService(mConnection);
            isServiceBind = false;
        }
        super.onDestroy();
    }
}
