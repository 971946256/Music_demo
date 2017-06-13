package com.example.ywhan.music_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SongFormAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<LocalFormType> parentList;
    private Map<String, List<LocalSongForm>> map;

    public SongFormAdapter(Context context, List<LocalFormType> parentList, Map<String, List<LocalSongForm>> map) {
        this.mContext = context;
        this.parentList = parentList;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(parentList.get(groupPosition).getTypeName()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map.get(parentList.get(groupPosition).getTypeName()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.song_form_parent_item, parent, false);
            holder.imgSetUp = (ImageView) convertView.findViewById(R.id.set_up);
            holder.tvTypeName = (TextView) convertView.findViewById(R.id.form_type);
            holder.tvFormNumber = (TextView) convertView.findViewById(R.id.form_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTypeName.setText(parentList.get(groupPosition).getTypeName());
        holder.tvFormNumber.setText(parentList.get(groupPosition).getFormNumber());
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.song_form_child_item, parent, false);
            holder.imgForm = (ImageView) convertView.findViewById(R.id.form_image);
            holder.tvFormName = (TextView) convertView.findViewById(R.id.form_name);
            holder.tvSongNumber = (TextView) convertView.findViewById(R.id.song_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String parentKey = parentList.get(groupPosition).getTypeName();
        LocalSongForm form = map.get(parentKey).get(childPosition);
        holder.imgForm.setImageResource(form.getImageId());
        holder.tvFormName.setText(form.getFormName());
        holder.tvSongNumber.setText(form.getSongNumber());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolder {
        ImageView imgSetUp;//歌单父级设置按钮
        ImageView imgForm;//歌单图片
        TextView tvTypeName;//歌单类型
        TextView tvFormNumber;//父级对应子项歌单的数量
        TextView tvFormName;//歌单名
        TextView tvSongNumber;//一个歌单中歌曲的数目
    }
}
