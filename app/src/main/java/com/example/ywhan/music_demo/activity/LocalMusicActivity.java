package com.example.ywhan.music_demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ywhan.music_demo.R;

/**
 * Created by Administrator on 2017/6/14/014.
 * 本地音乐主页面
 */

public class LocalMusicActivity extends AppCompatActivity {
    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LocalMusicActivity.this;
        setContentView(R.layout.local_music_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.local_music_toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.mipmap.lj);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*加载顶部菜单栏*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.local_music_menu,menu);
        return true;
    }

    /*顶部菜单栏按钮点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.local_music_search:
                Toast.makeText(mContext,"local search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.local_music_menu:
                Toast.makeText(mContext,"local menu",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
