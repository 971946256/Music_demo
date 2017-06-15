package com.example.ywhan.music_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.adapter.SongInforAdapter;
import com.example.ywhan.music_demo.entity.MusicInfor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15/015.
 * 本地歌曲，单曲
 */

public class SingleFragment extends Fragment implements View.OnClickListener{
    Context mContext;
    View view;
    ListView listView;
    private List<MusicInfor> mList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.local_music_listview,container,false);
        mContext = getContext();
        initView();
        initData();
        SongInforAdapter adapter = new SongInforAdapter(mContext,R.layout.song_message_item,mList);
        listView.setAdapter(adapter);
        return view;
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add(new MusicInfor("歌曲"+i,"歌手"+i,"专辑"+i));
        }
    }

    private void initView() {
        View playAll = view.findViewById(R.id.local_play_all);//播放全部
        LinearLayout play = (LinearLayout) playAll.findViewById(R.id.play_all);//播放全部
        play.setOnClickListener(this);
        LinearLayout more = (LinearLayout) playAll.findViewById(R.id.music_more);//多选
        more.setOnClickListener(this);
        TextView songNUm = (TextView) playAll.findViewById(R.id.form_song_number);//歌曲数量
        listView = (ListView) view.findViewById(R.id.local_song_listview);//歌曲列表
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_all:
                break;
            case R.id.music_more:
                break;
            default:
                break;
        }
    }
}
