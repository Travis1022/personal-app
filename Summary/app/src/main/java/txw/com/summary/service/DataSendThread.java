package txw.com.summary.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import txw.com.summary.Constant;
import txw.com.summary.entity.UserInfo;
import txw.com.summary.protocol.LoginProtocol;

/**
 * 照片文件发送线程
 * Created by txw on 2018/7/16.
 */
public class DataSendThread implements Runnable {

    /**
     * 目的IP
     */
    private String mDestinationIP;

    /**
     * 目的端口号
     */
    private int mDestinationPort;

    /**
     * Socket
     */
    private Socket mSocket;

    /**
     * 输出流
     */
    private OutputStream mOutputStream;

    /**
     * 输入流
     */
    private InputStream mInputStream;

    /**
     * 上传类型
     */
    private int mUploadType;


    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 用户信息
     */
    private UserInfo mUserInfo;


    /**
     * 登录信息
     *
     * @param context
     * @param userInfo
     */
    public DataSendThread(Context context, UserInfo userInfo) {
        this.mContext = context;
        this.mUserInfo = userInfo;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mDestinationIP = sharedPreferences.getString(Constant.KEY_DESTINATION_IP, Constant.CONSTANT_DESTINATION_IP);
        mDestinationPort = sharedPreferences.getInt(Constant.KEY_DESTINATION_PORT, Constant.DESTINATION_PORT);
    }

    /**
     * 照片文件信息
     *
     * @param context
     */
    public DataSendThread(Context context) {
        this.mContext = context;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mDestinationIP = sharedPreferences.getString(Constant.KEY_DESTINATION_IP, Constant.CONSTANT_DESTINATION_IP);
        mDestinationPort = sharedPreferences.getInt(Constant.KEY_DESTINATION_PORT, Constant.DESTINATION_PORT);
    }

    @Override
    public void run() {
        try {
            mSocket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(mDestinationIP, mDestinationPort);
            try {
                mSocket.connect(socketAddress, 10000);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mSocket.setSoTimeout(60000);
            mOutputStream = mSocket.getOutputStream();
            switch (mUploadType) {
                //登录
                case 0:
                    sendLoginProtocol(mUserInfo);
                    break;
                //注册
                case 1:
                    sendRegisterProtocol();
                    break;
                //照片文件上传
                case 2:
                    sendPhotoUploadNotice();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 写入数据
     *
     * @param bytes
     * @throws IOException
     */
    private void sendTCPPacket(byte[] bytes) throws IOException {
        mOutputStream.write(bytes);
    }

    /**
     * 登录信息
     *
     * @param userInfo
     */
    private void sendLoginProtocol(UserInfo userInfo) {
        LoginProtocol loginProtocol = new LoginProtocol(userInfo);
        try {
            sendTCPPacket(loginProtocol.outputData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册信息
     */
    private void sendRegisterProtocol() {
    }

    /**
     * 照片文件上传通知帧
     */
    private void sendPhotoUploadNotice() {
    }
}
