package com.example.ywhan.music_demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.adapter.MyMainViewPagerAdapter;
import com.example.ywhan.music_demo.fragment.DiscoverFragment;
import com.example.ywhan.music_demo.fragment.FriendsFragment;
import com.example.ywhan.music_demo.fragment.SingleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14/014.
 * 本地音乐主页面
 */

public class LocalMusicActivity extends AppCompatActivity implements View.OnClickListener {
    Context mContext;
    private TextView tvSong;//单曲
    private TextView tvSinger;//歌手
    private TextView tvAlbum;//专辑
    private TextView tvFolder;//文件夹
    private ViewPager viewPager;
    private View slider;//滑块
    private List<Fragment> mList = new ArrayList<>();
    private MyMainViewPagerAdapter adapter;
    private int pagerSelect;//当前页的页数
    private int sliderWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LocalMusicActivity.this;
        setContentView(R.layout.local_music_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.local_music_toolbar);
        setSupportActionBar(toolbar);
        initView();
        initDtat();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        initTabLineWidth();
    }

    private void initDtat() {
        Fragment songFragment = new SingleFragment();
        Fragment singerFragment = new DiscoverFragment();
        Fragment albumFragment = new FriendsFragment();
        Fragment folderFragment = new SingleFragment();
        mList.add(songFragment);
        mList.add(singerFragment);
        mList.add(albumFragment);
        mList.add(folderFragment);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new MyMainViewPagerAdapter(manager, mList);
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.mipmap.lj);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        View view = findViewById(R.id.local_tab);
        tvSong = (TextView) findViewById(R.id.music_name);
        tvSong.setOnClickListener(this);
        tvSong.setFocusable(true);
        tvSinger = (TextView) findViewById(R.id.music_singer);
        tvSinger.setOnClickListener(this);
        tvAlbum = (TextView) findViewById(R.id.music_album);
        tvAlbum.setOnClickListener(this);
        tvFolder = (TextView) findViewById(R.id.music_folder);
        tvFolder.setOnClickListener(this);
        slider = findViewById(R.id.slider);
        viewPager = (ViewPager) findViewById(R.id.local_music_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) slider.getLayoutParams();
                if (position == 0) {
                    lp.leftMargin = (int) (positionOffset * sliderWidth);
                    setTabSelected(positionOffset,tvSong,tvSinger);
                } else if (position == 1) {
                    lp.leftMargin = (int) (positionOffset * sliderWidth) + sliderWidth;
                    setTabSelected(positionOffset,tvSinger,tvAlbum);
                } else if (position == 2) {
                    lp.leftMargin = (int) (positionOffset * sliderWidth) + 2 * sliderWidth;
                    setTabSelected(positionOffset,tvAlbum,tvFolder);
                } else if (position == 3) {
                    lp.leftMargin = (int) (positionOffset * sliderWidth) + 3 * sliderWidth;
                }
                slider.setLayoutParams(lp);
            }

            //通过滑动切换时对toolbar中的按钮进行状态改变
            @Override
            public void onPageSelected(int position) {
                boolean[] state = new boolean[4];
                state[position] = true;
                updataViewPager(state[0], state[1], state[2], state[3]);
                pagerSelect = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*当屏幕滑动超过一半时就切换选中的页面*/
    public static void setTabSelected(float positionOffset,View before,View after) {
        if (positionOffset>=0.5){
            after.setSelected(true);
            before.setSelected(false);
        }else if (positionOffset<0.5){
            before.setSelected(true);
            after.setSelected(false);
        }
    }

    /*加载顶部菜单栏*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.local_music_menu, menu);
        return true;
    }

    /*顶部菜单栏按钮点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.local_music_search:
                Toast.makeText(mContext, "local search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.local_music_menu:
                Toast.makeText(mContext, "local menu", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    /*页面切换按钮点击跳转*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_name:
                viewPager.setCurrentItem(0);
                updataViewPager(true, false, false, false);
                break;
            case R.id.music_singer:
                viewPager.setCurrentItem(1);
                updataViewPager(false, true, false, false);
                break;
            case R.id.music_album:
                viewPager.setCurrentItem(2);
                updataViewPager(false, false, true, false);
                break;
            case R.id.music_folder:
                viewPager.setCurrentItem(3);
                updataViewPager(false, false, false, true);
                break;
            default:
                break;
        }
    }

    /* 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)*/
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        int screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) slider
                .getLayoutParams();
        lp.width = screenWidth / 4;
        sliderWidth = screenWidth / 4;
        slider.setLayoutParams(lp);
    }

    /*切换viewPager*/
    private void updataViewPager(boolean song, boolean singer, boolean album, boolean folder) {
        tvSong.setSelected(song);
        tvSinger.setSelected(singer);
        tvAlbum.setSelected(album);
        tvFolder.setSelected(folder);
    }
}
