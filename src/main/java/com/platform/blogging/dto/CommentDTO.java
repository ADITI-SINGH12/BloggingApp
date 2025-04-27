package com.platform.blogging.dto;

import com.platform.blogging.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private int id;

    private String content;
    private Post post;

}
