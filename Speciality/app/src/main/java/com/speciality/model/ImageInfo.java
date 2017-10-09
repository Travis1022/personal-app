package com.speciality.model;

import java.io.Serializable;

/**
 * 对应Item的Entity(图片信息)
 * Created by Travis on 2017/8/9.
 */

public class ImageInfo implements Serializable {
    private String imageName;
    private int imageId;


    public ImageInfo(String imageName, int imageId) {
        this.imageName = imageName;
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageName='" + imageName + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
