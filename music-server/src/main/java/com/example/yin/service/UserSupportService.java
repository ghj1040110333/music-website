package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.UserSupport;
import com.example.yin.model.request.UserSupportRequest;

/**
 * @author asus
 * @description 针对表【user_support】的数据库操作Service
 * @createDate 2022-06-11 16:06:28
 */
public interface UserSupportService extends IService<UserSupport> {

    Result isUserSupportComment(UserSupportRequest userSupportRequest);

    Result insertCommentSupport(UserSupportRequest userSupportRequest);

    Result deleteCommentSupport(UserSupportRequest userSupportRequest);
}
