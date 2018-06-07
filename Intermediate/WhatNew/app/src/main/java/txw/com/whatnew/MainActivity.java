package txw.com.whatnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import txw.com.whatnew.recycler.RecyclerActivity;

/**
 * create by txw on 2018-06-07
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_recycler)
    Button mBtnRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_recycler1,R.id.btn_recycler2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //使用RecyclerView实现线性列表以及网格列表
            case R.id.btn_recycler1:
                Intent intent = new Intent(this, RecyclerActivity.class);
                startActivity(intent);
                break;
            //使用RecyclerViews实现瀑布流
            case R.id.btn_recycler2:
                break;


        }
    }
}
