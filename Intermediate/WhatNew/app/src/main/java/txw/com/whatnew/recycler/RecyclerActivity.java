package txw.com.whatnew.recycler;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import txw.com.whatnew.R;

/**
 * create by txw on 2018-06-07
 */
public class RecyclerActivity extends AppCompatActivity {

    @BindView(R.id.rv_info)
    RecyclerView mRvInfo;

    private InfoAdapter mInfoAdapter;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * init layout
     */
    private void initView() {
        mRvInfo.setLayoutManager(new LinearLayoutManager(this));
        //item增加或者删除的时候的动画
        mRvInfo.setItemAnimator(new DefaultItemAnimator());
        initData();
        mInfoAdapter = new InfoAdapter(mDataList, this);
        mInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerActivity.this, "点击第" + (position + 1) + "条item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(RecyclerActivity.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mInfoAdapter.removeData(position);
                            }
                        }).show();

            }
        });
        mRvInfo.setAdapter(mInfoAdapter);

    }

    /**
     * init data
     */
    private void initData() {
        mDataList = new ArrayList<>();
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
        mDataList.add("test");
    }
}
