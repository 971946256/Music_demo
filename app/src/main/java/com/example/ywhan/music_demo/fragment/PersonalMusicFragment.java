package com.example.ywhan.music_demo.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.ywhan.music_demo.R;
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
    Integer[] topImage = new Integer[]{R.mipmap.ic_action_music_1, R.mipmap.ic_action_record, R.mipmap.ic_action_download
            , R.mipmap.ic_action_signal, R.mipmap.ic_action_heart};
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
        expandableListView = (ExpandableListView) view.findViewById(R.id.self_music_form);
    }

    private void initData() {
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
