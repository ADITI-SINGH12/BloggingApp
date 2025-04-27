package com.platform.blogging.service.impl;

import com.platform.blogging.dto.CommentDTO;
import com.platform.blogging.entity.Comment;
import com.platform.blogging.entity.Post;
import com.platform.blogging.exceptions.ResourceNotFoundException;
import com.platform.blogging.repository.CommentRepo;
import com.platform.blogging.repository.PostRepo;
import com.platform.blogging.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException(postId+" post not found"));
        commentDto.setPost(post);
        Comment savedComment = commentRepo.save(modelMapper.map(commentDto, Comment.class));
        return modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException(commentId+" comment not found"));
        commentRepo.delete(comment);
    }
}
