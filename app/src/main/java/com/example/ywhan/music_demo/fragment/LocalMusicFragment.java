package com.example.ywhan.music_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ywhan.music_demo.R;

/**
 * Created by Administrator on 2017/6/14/014.
 */

public class LocalMusicFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view ==null){
            view = inflater.inflate(R.layout.local_music_listview,container,false);

        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
