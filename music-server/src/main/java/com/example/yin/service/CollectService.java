package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.Collect;
import com.example.yin.model.request.CollectRequest;

public interface CollectService extends IService<Collect> {

    Result addCollection(CollectRequest addCollectRequest);

    Result existSongId(CollectRequest isCollectRequest);

    Result deleteCollect(Integer userId, Integer songId);

    Result collectionOfUser(Integer userId);
}
