package com.example.yin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yin.common.Result;
import com.example.yin.model.domain.Comment;
import com.example.yin.model.request.CommentRequest;

public interface CommentService extends IService<Comment> {

    Result addComment(CommentRequest addCommentRequest);

    Result updateCommentMsg(CommentRequest upCommentRequest);

    Result deleteComment(Integer id);

    Result commentOfSongId(Integer songId);

    Result commentOfSongListId(Integer songListId);

}
