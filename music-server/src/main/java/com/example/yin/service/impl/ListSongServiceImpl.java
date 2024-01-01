package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.Result;
import com.example.yin.mapper.ListSongMapper;
import com.example.yin.model.domain.ListSong;
import com.example.yin.model.request.ListSongRequest;
import com.example.yin.service.ListSongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.selectList(null);
    }

    @Override
    public Result updateListSongMsg(ListSongRequest updateListSongRequest) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(updateListSongRequest, listSong);
        if (listSongMapper.updateById(listSong) > 0) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    @Override
    public Result deleteListSong(Integer songId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",songId);
        if (listSongMapper.delete(queryWrapper) > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result addListSong(ListSongRequest addListSongRequest) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(addListSongRequest, listSong);
        if (listSongMapper.insert(listSong) > 0) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result listSongOfSongId(Integer songListId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return Result.success("查询成功", listSongMapper.selectList(queryWrapper));
    }

}
