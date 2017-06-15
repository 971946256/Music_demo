package com.example.ywhan.music_demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.adapter.MyMainViewPagerAdapter;
import com.example.ywhan.music_demo.fragment.DiscoverFragment;
import com.example.ywhan.music_demo.fragment.FriendsFragment;
import com.example.ywhan.music_demo.fragment.PersonalMusicFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.ywhan.music_demo.activity.LocalMusicActivity.setTabSelected;

/**
 * Created by ywhan on 2017/6/8/008.
 * 主函数
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Context mContext;
    private ViewPager viewPager;//主内容viewPager
    private DrawerLayout mDrawerLayout;//侧滑菜单
    private ImageView tabLocalMusic;//本地音乐按钮
    private ImageView tabDiscover;//网络音乐按钮
    private ImageView tabFriends;//朋友圈按钮
    private List<Fragment> mList = new ArrayList<>();//页面内容列表
    private MyMainViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MainActivity.this;
        setContentView(R.layout.activity_main);
        initView();
        initData();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    /*加载顶部按钮*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /*顶部Action按钮点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_music:
                Toast.makeText(mContext, "search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    /*布局初始化*/
    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.lo);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //点击测单中的任意按钮关闭侧滑菜单
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                    setTabSelected(positionOffset,tabLocalMusic,tabDiscover);
                }else if (position==1){
                    setTabSelected(positionOffset,tabDiscover,tabFriends);
                }
            }

            //通过滑动切换时对toolbar中的按钮进行状态改变
            @Override
            public void onPageSelected(int position) {
                boolean[] state = new boolean[3];
                state[position]=true;
                updataViewPager(state[0],state[1], state[2]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLocalMusic = (ImageView) findViewById(R.id.tab_local_music);
        tabLocalMusic.setOnClickListener(this);
        //默认本地音乐为第一页
        tabLocalMusic.setSelected(true);
        tabDiscover = (ImageView) findViewById(R.id.tab_discover);
        tabDiscover.setOnClickListener(this);
        tabFriends = (ImageView) findViewById(R.id.tab_friends);
        tabFriends.setOnClickListener(this);
    }

    /*数据初始化*/
    private void initData() {
        Fragment localMusic = new PersonalMusicFragment();
        Fragment discover = new DiscoverFragment();
        Fragment friends = new FriendsFragment();
        mList.add(localMusic);
        mList.add(discover);
        mList.add(friends);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new MyMainViewPagerAdapter(manager,mList);
    }


    /*toolbar页面切换按钮点击跳转*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_local_music:
                viewPager.setCurrentItem(0);
                updataViewPager(true, false, false);
                break;
            case R.id.tab_discover:
                viewPager.setCurrentItem(1);
                updataViewPager(false, true, false);
                break;
            case R.id.tab_friends:
                viewPager.setCurrentItem(2);
                updataViewPager(false, false, true);
                break;
           default:
                break;
        }
    }

    /*切换viewPager*/
    private void updataViewPager(boolean music, boolean discover, boolean friends) {
        tabLocalMusic.setSelected(music);
        tabDiscover.setSelected(discover);
        tabFriends.setSelected(friends);
    }
}
