package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.ListSong;
import com.example.yin.model.request.ListSongRequest;

import java.util.List;

public interface ListSongService extends IService<ListSong> {

    Result addListSong(ListSongRequest addListSongRequest);

    Result updateListSongMsg(ListSongRequest updateListSongRequest);

    Result deleteListSong(Integer songId);

    //看看这啥
    List<ListSong> allListSong();

    Result listSongOfSongId(Integer songListId);
}
