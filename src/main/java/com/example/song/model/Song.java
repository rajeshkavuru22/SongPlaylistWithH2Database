package com.example.song.model;

public class Song {
    private int songId;
    private String songName;
    private String lyricist;
    private String singer;
    private String musicDirector;

    public Song(int songId, String songName, String lyricist, String singer, String musicDirector) {
        this.songId = songId;
        this.songName = songName;
        this.lyricist = lyricist;
        this.singer = singer;
        this.musicDirector = musicDirector;
    }

    public void setSongId(int id) {
        this.songId = id;
    }

    public int getSongId() {
        return this.songId;
    }

    public void setSongName(String name) {
        this.songName = name;
    }

    public String getSongName() {
        return this.songName;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public String getLyricist() {
        return this.lyricist;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSinger() {
        return this.singer;
    }

    public void setMusicDirector(String musicDirector) {
        this.musicDirector = musicDirector;
    }

    public String getMusicDirector() {
        return this.musicDirector;
    }

}