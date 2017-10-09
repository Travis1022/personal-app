package com.speciality.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.speciality.R;
import com.speciality.model.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 对应RecyclerView的适配器
 * Created by txw on 2017/8/9.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder> {

    private List<ImageInfo> mImageInfoList;

    public MyAdapter(List<ImageInfo> imageInfoList) {
        mImageInfoList = new ArrayList<>();
        mImageInfoList = imageInfoList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.mIvDemo.setBackgroundResource(mImageInfoList.get(position).getImageId());
        holder.mTvDemo.setText(mImageInfoList.get(position).getImageName());
    }

    @Override
    public int getItemCount() {
        return mImageInfoList.size();
    }


    /**
     * 对应item的ViewHolder
     * Created by txw on 2017/8/9.
     */

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_demo)
        ImageView mIvDemo;
        @Bind(R.id.tv_demo)
        TextView mTvDemo;


        public ImageViewHolder(View itemView) {
            super(itemView);
            //绑定ItemView
            ButterKnife.bind(this, itemView);
        }

    }
}
