package com.platform.blogging.repository;


import com.platform.blogging.entity.Category;
import com.platform.blogging.entity.Post;
import com.platform.blogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    List<Post> findByPostTitle(String title);
    List<Post> findByPostTitleContaining(String keyword);
}
