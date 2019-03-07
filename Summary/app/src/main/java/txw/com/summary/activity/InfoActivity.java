package txw.com.summary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.OnClick;
import txw.com.summary.R;

/**
 * 基础信息
 * Created by txw on 2018/7/16.
 */
public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_login:
               break;
           case R.id.btn_register:
               break;
       }
    }
}
