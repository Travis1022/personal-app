package hzcec.com.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by txw on 2017/11/14.
 */

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
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
            Log.e("MyService", "binder==============");
        }
    }
}
