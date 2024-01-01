package com.example.yin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yin.common.Result;
import com.example.yin.mapper.UserSupportMapper;
import com.example.yin.model.domain.UserSupport;
import com.example.yin.model.request.UserSupportRequest;
import com.example.yin.service.UserSupportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asus
 * @description 针对表【user_support】的数据库操作Service实现
 * @createDate 2022-06-11 16:06:28
 */
@Service
public class UserSupportServiceImpl extends ServiceImpl<UserSupportMapper, UserSupport>
        implements UserSupportService {

    @Autowired
    private UserSupportMapper userSupportMapper;

    @Override
    public Result isUserSupportComment(UserSupportRequest userSupportRequest) {
        QueryWrapper<UserSupport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", userSupportRequest.getCommentId());
        queryWrapper.eq("user_id", userSupportRequest.getUserId());
        if (userSupportMapper.selectCount(queryWrapper) > 0) {
            return Result.success("您已取消点赞", true);
        } else {
            return Result.success("您已点赞", false);
        }
    }

    @Override
    public Result insertCommentSupport(UserSupportRequest userSupportRequest) {
        UserSupport userSupport = new UserSupport();
        BeanUtils.copyProperties(userSupportRequest, userSupport);
        if (userSupportMapper.insert(userSupport) > 0) {
            return Result.success("添加记录成功");
        }
        return Result.error("添加时,发生异常");
    }

    @Override
    public Result deleteCommentSupport(UserSupportRequest userSupportRequest) {
        QueryWrapper<UserSupport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", userSupportRequest.getCommentId());
        queryWrapper.eq("user_id", userSupportRequest.getUserId());
        if (userSupportMapper.delete(queryWrapper) > 0) {
            return Result.success("删除记录成功");
        }
        return Result.error("删除记录发生异常");
    }
}
