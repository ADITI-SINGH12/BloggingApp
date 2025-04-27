package com.platform.blogging.controller;

import com.platform.blogging.config.ApplicationConstants;
import com.platform.blogging.dto.PostDTO;
import com.platform.blogging.dto.payload.PostResponse;
import com.platform.blogging.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {
    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDTO> creatPost(@RequestParam("postTitle") String postTitle,
                                             @RequestParam("postContent") String postContent, @PathVariable int userId,
                                             @PathVariable int categoryId, @RequestParam("file") MultipartFile file) throws IOException {
   PostDTO savedPost =  postService.createPost(postTitle,postContent,userId,categoryId,file);
   return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable int postId){
        PostDTO updatepostDTO = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatepostDTO,HttpStatus.CREATED);
    }
    @DeleteMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable int postId){
        postService.deletePost(postId);
        return postId +" is deleted successfully";
    }
    @GetMapping("/auth/get/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = ApplicationConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.PAGE_SIZE, required = false) Integer pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.SORT_BY, required = false) String sortBy) {

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/post/get/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int postId){
        PostDTO postDTOList = postService.getPostById(postId);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    @GetMapping("/post/category/get/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable int categoryId){
        List<PostDTO> postDTOList = postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    @GetMapping("/post/user/get/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable int userId){
        List<PostDTO> postDTOList = postService.getAllPostByCategory(userId);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    @GetMapping("/post/title/get/{title}")
    public ResponseEntity<List<PostDTO>> getPostByTitle(@PathVariable String title){
        List<PostDTO> postDTOList = postService.searchPostByKeyword(title);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    @GetMapping("/post/keyword/get/{title}")
    public ResponseEntity<List<PostDTO>> getPostByKeyword(@PathVariable String title){
        List<PostDTO> postDTOList = postService.searchPostsTitleWithKewordContains(title);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
}
