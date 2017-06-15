package com.example.ywhan.music_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ywhan.music_demo.R;
import com.example.ywhan.music_demo.entity.LocalMusic;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9/009.
 * 顶部listView适配器
 */

public class LocalMusicAdapter extends ArrayAdapter<LocalMusic> {
    private int resourceId;

    public LocalMusicAdapter(Context context, int resource, List<LocalMusic> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LocalMusic localMusic = getItem(position);
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            //holder.imageView = (ImageView) convertView.findViewById(R.id.local_image);
            holder.tvName = (TextView) convertView.findViewById(R.id.local_name);
            holder.tvNumber = (TextView) convertView.findViewById(R.id.local_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(localMusic.getImageId());
        holder.tvName.setText(localMusic.getName());
        holder.tvNumber.setText(localMusic.getNumber());
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView tvName;
        TextView tvNumber;
    }
}
