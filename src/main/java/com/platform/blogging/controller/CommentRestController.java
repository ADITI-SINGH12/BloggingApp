package com.platform.blogging.controller;

import com.platform.blogging.dto.CommentDTO;
import com.platform.blogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    //create comment post id,comment delete comment
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable int postId){
       CommentDTO savedComment =  commentService.createComment(commentDTO,postId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public  String deleteComment(@PathVariable int commentId){
       commentService.deleteComment(commentId);
        return commentId+" is deleted successfully";
    }

}
