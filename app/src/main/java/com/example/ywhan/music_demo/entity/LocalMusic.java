package com.example.ywhan.music_demo.entity;

/**
 * Created by Administrator on 2017/6/9/009.
 * 本地歌曲顶部自定义类
 */

public class LocalMusic {
    private String name;
    private int imageId;
    private String number;

    public LocalMusic(String name, int imageId, String number) {
        this.name = name;
        this.imageId = imageId;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getNumber() {
        return number;
    }
}
