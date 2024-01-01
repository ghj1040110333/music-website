package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.Result;
import com.example.yin.mapper.RankListMapper;
import com.example.yin.model.domain.RankList;
import com.example.yin.model.request.RankListRequest;
import com.example.yin.service.RankListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RankListServiceImpl extends ServiceImpl<RankListMapper, RankList> implements RankListService {


    @Autowired
    private RankListMapper rankMapper;

    @Override
    public Result addRank(RankListRequest rankListAddRequest) {
        RankList rankList = new RankList();
        BeanUtils.copyProperties(rankListAddRequest, rankList);
        if (rankMapper.insert(rankList) > 0) {
            return Result.success("评价成功");
        } else {
            return Result.error("评价失败");
        }
    }

    @Override
    public Result rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        QueryWrapper<RankList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        Long rankNum = rankMapper.selectCount(queryWrapper);
        return Result.success(null, (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum);
    }

    @Override
    public Result getUserRank(Long consumerId, Long songListId) {
        return Result.success(null, rankMapper.selectUserRank(consumerId, songListId));
    }
}
