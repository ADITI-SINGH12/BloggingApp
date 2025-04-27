package com.platform.blogging.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    @NonNull
    private int postid;
    @NotBlank
    @Size(min = 5)
    private String postTitle;
    private String postImage;
    @NotBlank
    @Size(min = 10)
    private String postContent;
    private Date postCreatedDate;
    private CategoryDTO category;

    private UserDTO user;

    private Set<CommentDTO> comments = new HashSet<>();

}
