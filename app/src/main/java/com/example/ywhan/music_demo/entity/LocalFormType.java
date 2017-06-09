package com.example.ywhan.music_demo.entity;

/**
 * Created by Administrator on 2017/6/9/009.
 */

public class LocalFormType {
    private String typeName;
    private String formNumber;

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
