package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.Song;
import com.example.yin.model.request.SongRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SongService extends IService<Song> {

    Result addSong (SongRequest addSongRequest, MultipartFile mpfile);

    Result updateSongMsg(SongRequest updateSongRequest);

    Result updateSongUrl(MultipartFile urlFile, int id);

    Result updateSongPic(MultipartFile urlFile, int id);

    Result deleteSong(Integer id);

    Result allSong();

    Result songOfSingerId(Integer singerId);

    Result songOfId(Integer id);

    Result songOfSingerName(String name);
}
