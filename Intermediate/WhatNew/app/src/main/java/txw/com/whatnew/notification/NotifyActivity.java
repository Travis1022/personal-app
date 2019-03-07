package txw.com.whatnew.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import txw.com.whatnew.R;

/**
 * 通知栏
 * create by txw on 2018-06-08
 */
public class NotifyActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_simple)
    Button mBtnSimple;

    @BindView(R.id.btn_fold)
    Button mBtnFold;

    @BindView(R.id.btn_pad)
    Button mBtnPad;

    /**
     * NotificationManager
     */
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @OnClick({R.id.btn_simple, R.id.btn_fold, R.id.btn_pad})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //普通notification
            case R.id.btn_simple:
                Notification.Builder builder = new Notification.Builder(this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.drawable.logo);
                builder.setAutoCancel(true);
                builder.setContentTitle("普通通知");
                notificationManager.notify(1, builder.build());
                break;
            //折叠式notification
            case R.id.btn_fold:
                Notification.Builder builder1 = new Notification.Builder(this);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.huxiu.com"));
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, intent1, 0);
                builder1.setContentIntent(pendingIntent1);
                builder1.setSmallIcon(R.drawable.logo);
                builder1.setAutoCancel(true);
                builder1.setContentTitle("折叠式通知");
                Notification notification = builder1.build();
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
                notification.bigContentView = remoteViews;
                notificationManager.notify(1, notification);
                break;
            //悬挂式notification
            case R.id.btn_pad:
                break;
        }
    }
}
