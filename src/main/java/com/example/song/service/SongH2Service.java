
package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import com.example.song.model.SongRowMapper;
import com.example.song.model.Song;
import com.example.song.repository.SongRepository;

@Service
public class SongH2Service implements SongRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public Song getSongById(int songId) {

        try {
            Song songObj = db.queryForObject("select * from PLAYLIST where songId = ?", new SongRowMapper(), songId);
            return songObj;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ArrayList<Song> getSongs() {

        List<Song> songsList = db.query("select * from PLAYLIST", new SongRowMapper());
        ArrayList<Song> songsObj = new ArrayList<>(songsList);
        return songsObj;

    }

    @Override
    public Song addSong(Song songObj) {
        db.update("INSERT INTO PLAYLIST(songName, lyricist, singer, musicDirector) values(?,?,?,?)",
                songObj.getSongName(), songObj.getLyricist(), songObj.getSinger(), songObj.getMusicDirector());

        Song savedSong = db.queryForObject(
                "SELECT * FROM PLAYLIST WHERE songName=? and lyricist=? and singer=? and musicDirector=?",
                new SongRowMapper(),
                songObj.getSongName(), songObj.getLyricist(), songObj.getSinger(), songObj.getMusicDirector());
        return savedSong;
    }

    @Override
    public Song updateSong(int songId, Song songObj) {
        if (songObj.getSongName() != null) {
            db.update("update PLAYLIST set songName = ? where songId = ?", songObj.getSongName(), songId);
        }
        if (songObj.getLyricist() != null) {
            db.update("update PLAYLIST set lyricist = ? where songId = ?", songObj.getLyricist(), songId);
        }
        if (songObj.getSinger() != null) {
            db.update("update PLAYLIST set singer = ? where songId = ?", songObj.getSinger(), songId);
        }
        if (songObj.getMusicDirector() != null) {
            db.update("update PLAYLIST set musicDirector = ? where songId = ?", songObj.getMusicDirector(), songId);
        }

        return getSongById(songId);
    }

    @Override
    public void deleteSong(int songId) {
        try {
            db.update("delete from PLAYLIST where songId = ?", songId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}