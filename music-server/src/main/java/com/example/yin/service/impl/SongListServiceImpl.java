package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.Result;
import com.example.yin.mapper.SongListMapper;
import com.example.yin.model.domain.SongList;
import com.example.yin.model.request.SongListRequest;
import com.example.yin.service.SongListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public Result updateSongListMsg(SongListRequest updateSongListRequest) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(updateSongListRequest, songList);
        if (songListMapper.updateById(songList) > 0) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    @Override
    public Result deleteSongList(Integer id) {
        if (songListMapper.deleteById(id) > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result allSongList() {
        return Result.success(null, songListMapper.selectList(null));
    }

    @Override
    public Result likeTitle(String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return Result.success(null, songListMapper.selectList(queryWrapper));
    }

    @Override
    public Result likeStyle(String style) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style",style);
        return Result.success(null, songListMapper.selectList(queryWrapper));
    }

    @Override
    public Result addSongList(SongListRequest addSongListRequest) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(addSongListRequest, songList);
        String pic = "/img/songListPic/123.jpg";
        songList.setPic(pic);
        if (songListMapper.insert(songList) > 0) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @Override
    public Result updateSongListImg(MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return Result.fatal("上传失败" + e.getMessage());
        }
        SongList songList = new SongList();
        songList.setId(id);
        songList.setPic(imgPath);
        if (songListMapper.updateById(songList) > 0) {
            return Result.success("上传成功", imgPath);
        } else {
            return Result.error("上传失败");
        }
    }
}
