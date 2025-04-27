package com.platform.blogging.service.impl;

import com.platform.blogging.dto.PostDTO;
import com.platform.blogging.dto.payload.PostResponse;
import com.platform.blogging.entity.Category;
import com.platform.blogging.entity.Post;
import com.platform.blogging.entity.User;
import com.platform.blogging.exceptions.ResourceNotFoundException;
import com.platform.blogging.repository.CategoryRepo;
import com.platform.blogging.repository.PostRepo;
import com.platform.blogging.repository.UserRepo;
import com.platform.blogging.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
   private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
   private ModelMapper modelMapper;
    @Autowired
    private FileServiceImpl fileService;
    @Value("${project.image}")
    private String path;

    @Override
    public PostDTO createPost(String postTitle,String postContent, Integer userId, Integer categoryId, MultipartFile file) throws IOException {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(userId+ " is user is not found"));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(categoryId+ " is category is not found"));
      //  Post post = this.modelMapper.map(postDTO, Post.class);//only come post title, and description other attribute we should set manually set
        Post post = new Post();
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        String filename = fileService.uploadImages(path,file);
        post.setPostImage(filename);//default image
        post.setPostCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepo.save(post);

        return this.modelMapper.map(savedPost, PostDTO.class);

    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException(postId+" this is not found"));
        post.setPostTitle(postDTO.getPostTitle());
        post.setPostContent(post.getPostContent());
        post.setPostCreatedDate(new Date());
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException(postId+" this is not found"));
        fileService.deleteResource(path+post.getPostImage());
        postRepo.delete(post);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
       Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException(postId+" this is not found"));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page<Post> pageOfPost = this.postRepo.findAll(pageable);
        // List<Post> findAllPost = this.postRepository.findAll();//old one-without
        // pagination
        List<Post> findAllPost = pageOfPost.getContent();

        List<PostDTO> postDto = findAllPost.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());


        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDto);
        postResponse.setPageNumber(pageOfPost.getNumber());
        postResponse.setPageSize(pageOfPost.getSize());
        postResponse.setTotalElemets(pageOfPost.getTotalElements());
        postResponse.setTotalPages(pageOfPost.getTotalPages());
        postResponse.setLastPage(pageOfPost.isLast());


        return postResponse;
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(categoryId+ " is category is not found"));
        List<Post> postList = postRepo.findByCategory(category);
        List<PostDTO> postDTO = postList.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTO;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(userId+ " is user is not found"));
        List<Post> postList = postRepo.findByUser(user);
        List<PostDTO> postDTOList = postList.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<PostDTO> searchPostByKeyword(String keyword) {
        List<Post> postList =   postRepo.findByPostTitle(keyword);
        List<PostDTO> postDTOList = postList.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<PostDTO> searchPostsTitleWithKewordContains(String keyword) {
        List<Post> postList =   postRepo.findByPostTitleContaining(keyword);
        List<PostDTO> postDTOList = postList.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }
}
