package com.example.ywhan.music_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.entity.LocalSongForm;
import com.example.ywhan.music_demo.entity.MusicInfor;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9/009.
 * 歌单的适配器
 */

public class SongInforAdapter extends ArrayAdapter<MusicInfor> {
    private int resourceId;

    public SongInforAdapter(Context context, int resource, List<MusicInfor> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MusicInfor infor = getItem(position);
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder.imgPlay = (ImageView) convertView.findViewById(R.id.play_icon);
            holder.imgLocal = (ImageView) convertView.findViewById(R.id.local_flag);
            holder.tvSongName = (TextView) convertView.findViewById(R.id.song_name);
            holder.tvSingerName = (TextView) convertView.findViewById(R.id.folder_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgPlay.setVisibility(View.GONE);
        holder.tvSongName.setText(infor.getSongName());
        holder.tvSingerName.setText(infor.getSingerName()+" - "+infor.getAlbumName());
        return convertView;
    }

    private class ViewHolder {
        ImageView imgPlay;//正在播放图片
        ImageView imgLocal;//是否存在本地歌曲
        TextView tvSongName;//歌曲名
        TextView tvSingerName;//歌手名
    }
}
