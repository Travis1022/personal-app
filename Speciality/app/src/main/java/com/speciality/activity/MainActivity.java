package com.speciality.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.speciality.R;
import com.speciality.adapter.MyAdapter;
import com.speciality.model.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_demo)
    RecyclerView mRvDemo;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置布局和动画
        mRvDemo.setLayoutManager(new LinearLayoutManager(this));
        mRvDemo.setItemAnimator(new DefaultItemAnimator());
        //绑定数据
        List<ImageInfo> list = new ArrayList<>();
        list.add(new ImageInfo("哈士奇1", R.drawable.dog_1));
        list.add(new ImageInfo("哈士奇2", R.drawable.dog_2));
        list.add(new ImageInfo("哈士奇1", R.drawable.dog_1));
        list.add(new ImageInfo("哈士奇2", R.drawable.dog_2));
        list.add(new ImageInfo("哈士奇1", R.drawable.dog_1));
        list.add(new ImageInfo("哈士奇2", R.drawable.dog_2));
        list.add(new ImageInfo("哈士奇1", R.drawable.dog_1));
        //添加适配器
        myAdapter = new MyAdapter(list);
        mRvDemo.setAdapter(myAdapter);

    }
}
