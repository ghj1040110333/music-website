package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.Consumer;
import com.example.yin.model.request.ConsumerRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface ConsumerService extends IService<Consumer> {

    Result addUser(ConsumerRequest registryRequest);

    Result updateUserMsg(ConsumerRequest updateRequest);

    Result updateUserAvator(MultipartFile avatorFile, int id);

    Result updatePassword(ConsumerRequest updatePasswordRequest);

    boolean existUser(String username);

    boolean verityPasswd(String username, String password);

    Result deleteUser(Integer id);

    Result allUser();

    Result userOfId(Integer id);

    Result loginStatus(ConsumerRequest loginRequest, HttpSession session);

}
