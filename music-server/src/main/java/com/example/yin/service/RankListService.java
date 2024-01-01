package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.RankList;
import com.example.yin.model.request.RankListRequest;

public interface RankListService extends IService<RankList> {

    Result addRank(RankListRequest rankListAddRequest);

    Result rankOfSongListId(Long songListId);

    Result getUserRank(Long consumerId, Long songListId);

}
