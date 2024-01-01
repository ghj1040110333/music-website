package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.SongList;
import com.example.yin.model.request.SongListRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SongListService extends IService<SongList> {

    Result addSongList(SongListRequest addSongListRequest);

    Result updateSongListMsg(SongListRequest updateSongListRequest);

    Result updateSongListImg(MultipartFile avatorFile, int id);

    Result deleteSongList(Integer id);

    Result allSongList();

    Result likeTitle(String title);

    Result likeStyle(String style);
}
