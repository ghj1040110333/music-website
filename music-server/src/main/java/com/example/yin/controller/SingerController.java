package com.example.yin.controller;

import com.example.yin.common.Result;
import com.example.yin.model.request.SingerRequest;
import com.example.yin.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SingerController {

    @Autowired
    private SingerService singerService;


    // 添加歌手
    @PostMapping("/singer/add")
    public Result addSinger(@RequestBody SingerRequest addSingerRequest) {
        return singerService.addSinger(addSingerRequest);
    }

    // 删除歌手
    @DeleteMapping("/singer/delete")
    public Result deleteSinger(@RequestParam int id) {
        return singerService.deleteSinger(id);
    }

    // 返回所有歌手
    @GetMapping("/singer")
    public Result allSinger() {
        return singerService.allSinger();
    }

    // 根据歌手名查找歌手
    @GetMapping("/singer/name/detail")
    public Result singerOfName(@RequestParam String name) {
        return singerService.singerOfName(name);
    }

    // 根据歌手性别查找歌手
    @GetMapping("/singer/sex/detail")
    public Result singerOfSex(@RequestParam int sex) {
        return singerService.singerOfSex(sex);
    }

    // 更新歌手信息
    @PostMapping("/singer/update")
    public Result updateSingerMsg(@RequestBody SingerRequest updateSingerRequest) {
        return singerService.updateSingerMsg(updateSingerRequest);
    }

    // 更新歌手头像
    @PostMapping("/singer/avatar/update")
    public Result updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return singerService.updateSingerPic(avatorFile, id);
    }
}
