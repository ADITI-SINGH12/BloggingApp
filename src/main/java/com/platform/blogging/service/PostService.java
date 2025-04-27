package com.platform.blogging.service;

import com.platform.blogging.dto.PostDTO;
import com.platform.blogging.dto.payload.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDTO createPost(String postTitle,String postContent, Integer userId, Integer postId, MultipartFile file) throws IOException;

    //update post
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get Single post
    PostDTO getPostById(Integer postId);

    //get list of post
    //List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    //get post by category

    List<PostDTO> getAllPostByCategory(Integer categoryId);

    //get all post by user
    List<PostDTO> getAllPostByUser(Integer userId);

    //searchPost
    List<PostDTO> searchPostByKeyword(String keyword);

    List<PostDTO> searchPostsTitleWithKewordContains(String keyword);
}
