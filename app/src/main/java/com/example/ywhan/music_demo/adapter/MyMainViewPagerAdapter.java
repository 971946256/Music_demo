package com.example.ywhan.music_demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14/014.
 * 三个主页面的适配器
 */

public class MyMainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList<>();//页面列表

    public MyMainViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }
}
