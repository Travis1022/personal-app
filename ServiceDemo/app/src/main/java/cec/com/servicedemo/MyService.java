package cec.com.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by txw on 2017/11/14.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        Log.i(TAG, "onStartCommand()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public MyBinder onBind(Intent intent) {
        return new MyBinder();
    }


    class MyBinder extends Binder {

        MyBinder() {
        }

        public void test() {
            Log.i(TAG, "binder==============");
        }
    }
}
