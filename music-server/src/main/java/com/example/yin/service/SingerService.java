package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.Singer;
import com.example.yin.model.request.SingerRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SingerService extends IService<Singer> {

    Result addSinger (SingerRequest addSingerRequest);

    Result updateSingerMsg(SingerRequest updateSingerRequest);

    Result updateSingerPic(MultipartFile avatorFile, int id);

    Result deleteSinger(Integer id);

    Result allSinger();

    Result singerOfName(String name);

    Result singerOfSex(Integer sex);
}
