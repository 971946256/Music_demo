package com.example.ywhan.music_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.entity.LocalFormType;
import com.example.ywhan.music_demo.entity.LocalSongForm;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/9/009.
 * 歌单的适配器
 */

public class SongFormAdapter extends ArrayAdapter<LocalSongForm> {
    private int resourceId;

    public SongFormAdapter(Context context, int resource, List<LocalSongForm> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LocalSongForm form = getItem(position);
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder.imgForm = (ImageView) convertView.findViewById(R.id.form_image);
            holder.tvFormName = (TextView) convertView.findViewById(R.id.form_name);
            holder.tvSongNumber = (TextView) convertView.findViewById(R.id.song_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgForm.setImageResource(form.getImageId());
        holder.tvFormName.setText(form.getFormName());
        holder.tvSongNumber.setText(form.getSongNumber());
        return convertView;
    }

    private class ViewHolder {
        ImageView imgForm;//歌单图片
        TextView tvFormName;//歌单名
        TextView tvSongNumber;//一个歌单中歌曲的数目
    }
}
