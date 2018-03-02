package txw.advanceddemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 使用AsyncTask.
 * <p>
 * 根据日志分析AsyncTask的运行原理，结合源码查看
 * <p>
 * create by txw on 2018-03-01
 */
public class AsyncActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "AsyncActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        Button btnAsync = (Button) findViewById(R.id.btn_asyn);
        btnAsync.setOnClickListener(this);

        Button btnHandler = (Button) findViewById(R.id.btn_handler);
        btnHandler.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_asyn:
                //1.使用AsyncTask
                //new MyAsyncTask().execute("travis test");

                //2.AsyncTask原理：串行处理
//                for (int i = 0; i < 8; i++) {
//                    Log.d(TAG, "asyncTask post Task:" + i);
//                    new TestAsyncTask().execute("asyncTask times:" + i);
//                }


                //2.0 AsyncTask使用并行线程处理方式一
//                new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "leader0 test");
//                new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "leader1 test");
//                new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "leader2 test");
//                new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "leader3 test");

                //2.1使用并行线程处理方式二：传入线程池
                new MyAsyncTask().executeOnExecutor(Executors.newCachedThreadPool(), "leader0 test");
                new MyAsyncTask().executeOnExecutor(Executors.newCachedThreadPool(), "leader1 test");
                new MyAsyncTask().executeOnExecutor(Executors.newCachedThreadPool(), "leader2 test");
                new MyAsyncTask().executeOnExecutor(Executors.newCachedThreadPool(), "leader3 test");

                //2.2使用并行线程处理方式三:传入自定义的线程池
                Executor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
                new MyAsyncTask().executeOnExecutor(threadPoolExecutor, "leader0 test");
                new MyAsyncTask().executeOnExecutor(threadPoolExecutor, "leader1 test");
                new MyAsyncTask().executeOnExecutor(threadPoolExecutor, "leader2 test");
                new MyAsyncTask().executeOnExecutor(threadPoolExecutor, "leader3 test");


                break;
            case R.id.btn_handler:
                //使用Handler
                final MyHandler myHandler = new MyHandler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            Message message = new Message();
                            message.what = 1;
                            message.obj = new Integer(i);
                            Log.e(TAG, "post message:" + i);
                            myHandler.sendMessage(message);
                        }
                    }
                }).start();
                break;
        }
    }


    /**
     * AsyncTask
     */
    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            //子线程执行
            Log.i(TAG, "===========doInBackground in:" + strings[0] + "===============");
            int times = 3;
            for (int i = 0; i < times; i++) {
                publishProgress(i);
                //提交之后，会执行onProcessUpdate方法
            }
            Log.i(TAG, "doInBackground out");
            return "over";
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "onPostExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //主线程（UI线程）中执行
            Log.i(TAG, "onProgressUpdate:" + values[0]);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Log.i(TAG, "onCancelled");

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG, "onCancelled");
        }
    }


    private class TestAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.i(TAG, "doInBackground in thread:" + strings[0]);
            try {
                int times = 5;
                for (int i = 0; i < times; i++) {
                    Log.i(TAG, "thread alive:" + i + " for times " + strings[0]); //这个doInBackground就打印一个Log，然后sleep 20 毫秒
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "over";
        }
    }


    /**
     * Handler
     */
    private class MyHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handle message:" + msg.obj);
                    break;
                default:
                    break;
            }
        }
    }
}
