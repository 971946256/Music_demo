package com.example.ywhan.music_demo.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class PersonalMusicFragment extends Fragment implements View.OnClickListener {
    Context mContext;
    View view;
    ListView listView1;//创建的歌单列表
    ListView listView2;//收藏的歌单列表
    ImageView state1;//创建的歌单展开箭头
    ImageView state2;//收藏的歌单展开箭头
    private String[] top = new String[]{"本地音乐", "最近播放", "下载管理", "我的收藏"};//栏目名称
    private Integer[] topImage = new Integer[]{R.mipmap.wj, R.mipmap.x4, R.mipmap.wd, R.mipmap.w8};//顶部图标
    private String[] folderName = new String[]{"创建的歌单", "收藏的歌单"};//歌单类型名称
    private List<LocalSongForm> childList = new ArrayList<>();//歌单子级列表

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContext = getContext();
        view = inflater.inflate(R.layout.personal_music, container, false);
        initView();
        initData();
        SongFormAdapter adapter1 = new SongFormAdapter(mContext, R.layout.song_form_child_item, childList);
        listView1.setAdapter(adapter1);
        listView1.setFocusable(false);
        new ListViewParamsUtils().setListViewHeightBasedOnChildren(listView1);
        listView2.setAdapter(adapter1);
        new ListViewParamsUtils().setListViewHeightBasedOnChildren(listView2);
        return view;
    }

    private void initView() {
        View localMusic = view.findViewById(R.id.local_music);//本地音乐
        ImageView imgLocal1 = (ImageView) localMusic.findViewById(R.id.top_image);//头部图标
        ImageView imgPlay1 = (ImageView) localMusic.findViewById(R.id.play_now);//尾部图标
        TextView tvLocal1 = (TextView) localMusic.findViewById(R.id.local_name);//栏目名称
        TextView tvLocalNum1 = (TextView) localMusic.findViewById(R.id.local_number);//包含音乐数量
        //设置布局具体内容
        setTopView(imgLocal1, imgPlay1, tvLocal1, tvLocalNum1, 0);
        localMusic.setOnClickListener(this);

        View recentlyPlay = view.findViewById(R.id.recently_play);//最近播放
        ImageView imgLocal2 = (ImageView) recentlyPlay.findViewById(R.id.top_image);
        ImageView imgPlay2 = (ImageView) recentlyPlay.findViewById(R.id.play_now);
        TextView tvLocal2 = (TextView) recentlyPlay.findViewById(R.id.local_name);
        TextView tvLocalNum2 = (TextView) recentlyPlay.findViewById(R.id.local_number);
        setTopView(imgLocal2, imgPlay2, tvLocal2, tvLocalNum2, 1);
        recentlyPlay.setOnClickListener(this);

        View downManage = view.findViewById(R.id.down_manage);//下载管理
        ImageView imgLocal3 = (ImageView) downManage.findViewById(R.id.top_image);
        ImageView imgPlay3 = (ImageView) downManage.findViewById(R.id.play_now);
        TextView tvLocal3 = (TextView) downManage.findViewById(R.id.local_name);
        TextView tvLocalNum3 = (TextView) downManage.findViewById(R.id.local_number);
        setTopView(imgLocal3, imgPlay3, tvLocal3, tvLocalNum3, 2);
        downManage.setOnClickListener(this);

        View myCollection = view.findViewById(R.id.my_collection);//我的收藏
        ImageView imgLocal4 = (ImageView) myCollection.findViewById(R.id.top_image);
        ImageView imgPlay4 = (ImageView) myCollection.findViewById(R.id.play_now);
        TextView tvLocal4 = (TextView) myCollection.findViewById(R.id.local_name);
        TextView tvLocalNum4 = (TextView) myCollection.findViewById(R.id.local_number);
        setTopView(imgLocal4, imgPlay4, tvLocal4, tvLocalNum4, 3);
        myCollection.setOnClickListener(this);

        View foundFolder = view.findViewById(R.id.found_folder);//创建的歌单
        state1 = (ImageView) foundFolder.findViewById(R.id.listview_state);//展开状态箭头
        TextView folderName1 = (TextView) foundFolder.findViewById(R.id.form_type);//歌单类型名
        TextView folderNum1 = (TextView) foundFolder.findViewById(R.id.form_number);//包含歌单数量
        ImageView setUp1 = (ImageView) foundFolder.findViewById(R.id.form_setup);//歌单设置
        listView1 = (ListView) foundFolder.findViewById(R.id.music_bottom_listview);//歌单的listView
        folderName1.setText(folderName[0]);
        folderNum1.setText("5");
        foundFolder.setOnClickListener(this);

        View collectionFolder = view.findViewById(R.id.collection_folder);//收藏的歌单
        state2 = (ImageView) collectionFolder.findViewById(R.id.listview_state);
        TextView folderName2 = (TextView) collectionFolder.findViewById(R.id.form_type);
        TextView folderNum2 = (TextView) collectionFolder.findViewById(R.id.form_number);
        ImageView setUp2 = (ImageView) collectionFolder.findViewById(R.id.form_setup);
        listView2 = (ListView) collectionFolder.findViewById(R.id.music_bottom_listview);
        folderName2.setText(folderName[1]);
        folderNum2.setText("5");
        collectionFolder.setOnClickListener(this);
    }

    /*设置顶部布局的内容*/
    private void setTopView(ImageView imgLocal, ImageView imgPlay, TextView tvLocal, TextView tvLocalNum, int flag) {
        for (int i = 0; i < top.length; i++) {
            if (i == flag) {
                imgLocal.setImageResource(topImage[i]);
                imgPlay.setVisibility(View.GONE);
                tvLocal.setText(top[i]);
                tvLocalNum.setText("0");
            }
        }
    }

    /*数据初始化*/
    private void initData() {
        childList.clear();
        for (int i = 0; i < 10; i++) {
            LocalSongForm form = new LocalSongForm(topImage[1], top[1], String.valueOf(i));
            childList.add(form);
        }
    }

    /*Activity跳转*/
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.local_music://本地歌曲
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case R.id.recently_play://最近播放
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case R.id.down_manage://下载管理
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case R.id.my_collection://我的收藏
                intent = new Intent(mContext, LocalMusicActivity.class);
                startActivity(intent);
                break;
            case R.id.found_folder://创建的歌单
                imgAnimator(listView1,state1);
                break;
            case R.id.collection_folder://收藏的歌单
                imgAnimator(listView2,state2);
                break;
            default:
                break;
        }
    }

    /*列表展开箭头动画*/
    private void imgAnimator(ListView listView,ImageView imageView) {
        if (listView.getVisibility() == View.VISIBLE) {
            listView.setVisibility(View.GONE);
            //控件、动画类别、动画参数
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotation",0f,-90f);
            //动画时间，1000=1秒
            animator.setDuration(100);
            //动画开始
            animator.start();
        } else {
            listView.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotation",-90f,0f);
            animator.setDuration(100);
            animator.start();
        }
    }

    /*listview高度计算*/
    private class ListViewParamsUtils {
        void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }
            //初始化高度
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                //计算子项View的宽高，注意listview所在的要是linearlayout布局
                listItem.measure(0, 0);
                //统计所有子项的总高度
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            /*
         * listView.getDividerHeight()获取子项间分隔符占用的高度，有多少项就乘以多少个,
         * 5是在listview中填充的子view的padding值
         * params.height最后得到整个ListView完整显示需要的高度
         * 最后将params.height设置为listview的高度
         */
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount())) + 5;
            listView.setLayoutParams(params);
        }
    }
}
