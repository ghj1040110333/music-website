package com.example.yin.controller;

import com.example.yin.common.Result;
import com.example.yin.model.request.RankListRequest;
import com.example.yin.service.RankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RankListController {

    @Autowired
    private RankListService rankListService;


    // 提交评分
    @PostMapping("/rankList/add")
    public Result addRank(@RequestBody RankListRequest rankListAddRequest) {
        return rankListService.addRank(rankListAddRequest);
    }

    // 获取指定歌单的评分
    @GetMapping("/rankList")
    public Result rankOfSongListId(@RequestParam Long songListId) {
        return rankListService.rankOfSongListId(songListId);
    }

    // 获取指定用户的歌单评分
    @GetMapping("/rankList/user")
    public Result getUserRank(@RequestParam Long consumerId, @RequestParam Long songListId) {
        return rankListService.getUserRank(consumerId, songListId);
    }
}
