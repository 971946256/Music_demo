package com.example.ywhan.music_demo.entity;

/**
 * Created by Administrator on 2017/6/9/009.
 */

public class LocalSongForm {
    private int imageId;
    private String formName;
    private String songNumber;

    public LocalSongForm(int imageId, String formName, String songNumber) {
        this.imageId = imageId;
        this.formName = formName;
        this.songNumber = songNumber;
    }

    public int getImageId() {
        return imageId;
    }

    public String getFormName() {
        return formName;
    }

    public String getSongNumber() {
        return songNumber;
    }
}
