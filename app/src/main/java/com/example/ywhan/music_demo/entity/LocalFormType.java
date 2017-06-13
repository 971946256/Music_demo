package com.example.ywhan.music_demo.entity;

/**
 * Created by Administrator on 2017/6/9/009.
 * 歌单父级自定义类
 */

public class LocalFormType {
    private String typeName;//歌单类型
    private String formNumber;//歌单数量

    public LocalFormType(String typeName, String formNumber) {
        this.typeName = typeName;
        this.formNumber = formNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getFormNumber() {
        return formNumber;
    }
}
