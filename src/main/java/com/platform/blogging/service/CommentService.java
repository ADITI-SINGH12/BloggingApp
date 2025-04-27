package com.platform.blogging.service;

import com.platform.blogging.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
