package txw.com.summary.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.Serializable;

import txw.com.summary.entity.UserInfo;

/**
 * Created by txw on 2018/7/16.
 */
public class NetService extends Service {

    /**
     * 协议中的DLE字节
     */
    private static final byte DEL = 0x10;

    /**
     * 协议中的STX字节
     */
    private static final byte STX = 0x02;

    /**
     * 协议中的TX字节
     */
    private static final byte TX = 0x03;



    private final Binder myBinder = new MyBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }


    /**
     * Binder
     */
    public class MyBinder extends Binder implements Serializable {

        /**
         * 登录
         */
        public void checkLogin(UserInfo userInfo) {
            DataSendThread dataSendThread = new DataSendThread(getApplicationContext(), userInfo);
            dataSendThread.run();
        }


        /**
         * 注册
         */
        public void checkRegister() {

        }


        /**
         * 照片文件上传
         */
        public void uploadPhoto() {

        }
    }
}
