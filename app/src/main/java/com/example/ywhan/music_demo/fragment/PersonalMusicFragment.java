package com.example.ywhan.music_demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.activity.LocalMusicActivity;
import com.example.ywhan.music_demo.adapter.LocalMusicAdapter;
import com.example.ywhan.music_demo.adapter.SongFormAdapter;
import com.example.ywhan.music_demo.entity.LocalFormType;
import com.example.ywhan.music_demo.entity.LocalMusic;
import com.example.ywhan.music_demo.entity.LocalSongForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/9/009.
 * 本地歌曲首页
 */

public class PersonalMusicFragment extends Fragment {
    Context mContext;
    View view;
    ListView listView;//顶部listView
    ExpandableListView expandableListView;//歌单listView
    String[] top = new String[]{"本地音乐", "最近播放", "下载管理", "我的电台", "我的收藏"};
    Integer[] topImage = new Integer[]{R.mipmap.wj, R.mipmap.x4, R.mipmap.wd, R.mipmap.x6, R.mipmap.w8};
    private List<LocalMusic> musicList = new ArrayList<>();//本地歌曲栏目列表
    private List<LocalFormType> parentList = new ArrayList<>();//歌单父级列表
    private List<LocalSongForm> childList = new ArrayList<>();//歌单子级列表
    private Map<String, List<LocalSongForm>> map = new HashMap<>();//父级对应子级的集合

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContext = getContext();
        view = inflater.inflate(R.layout.personal_music, container, false);
        initView();
        initData();
        LocalMusicAdapter adapter = new LocalMusicAdapter(mContext, R.layout.music_top_item, musicList);
        listView.setAdapter(adapter);
        SongFormAdapter formAdapter = new SongFormAdapter(mContext, parentList, map);
        expandableListView.setAdapter(formAdapter);
        return view;
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.music_top_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocalMusic music = musicList.get(position);
                setActivity(music);
            }
        });
        expandableListView = (ExpandableListView) view.findViewById(R.id.self_music_form);
    }

    /*Activity跳转*/
    private void setActivity(LocalMusic music) {
        Intent intent;
        switch (music.getName()) {
            case "本地音乐":
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case "最近播放":
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case "下载管理":
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case "我的电台":
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case "我的收藏":
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /*数据初始化*/
    private void initData() {
        musicList.clear();
        parentList.clear();
        childList.clear();
        for (int i = 0; i < top.length; i++) {
            LocalMusic music = new LocalMusic(top[i], topImage[i], "0");
            musicList.add(music);
        }
        for (int j = 0; j < 2; j++) {
            childList.clear();
            for (int i = 0; i < top.length; i++) {
                LocalSongForm form = new LocalSongForm(topImage[i], top[i], String.valueOf(i));
                childList.add(form);
            }
            String typeName = "创建的歌单" + String.valueOf(j);
            parentList.add(new LocalFormType(typeName, "5"));
            map.put(typeName, childList);
        }
    }

}
