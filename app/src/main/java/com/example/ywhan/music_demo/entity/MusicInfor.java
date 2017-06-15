package com.example.ywhan.music_demo.entity;

/**
 * Created by Administrator on 2017/6/15/015.
 */

public class MusicInfor {
    private String songName;//歌曲名
    private String singerName;//歌手名
    private String albumName;//专辑名称

    public MusicInfor(String songName, String singerName, String albumName) {
        this.songName = songName;
        this.singerName = singerName;
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public String getAlbumName() {
        return albumName;
    }
}
