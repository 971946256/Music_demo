package com.example.ywhan.music_demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

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

    /*销毁Fragment页面*/
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //注释掉这一行以后就会防止Fragment被销毁，否则根据viewPager默认的缓存机制只缓存当前页面的左右相邻的两个页面，
        //当滑动到第三页时就会默认销毁第一页，注释掉之后就不会销毁了
        //super.destroyItem(container, position, object);
    }
}
