package com.socialize.repository;

import com.socialize.model.Post;
import com.socialize.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // USER defined methods to get user specific posts
    // the other methods like findPostByID, findAllPost will be provided by default  as we extend the jpaRepository interface
  List<Post> findPostByUserOrderById(User user);
  List<Post> findAllByOrderByIdDesc(); // for the latest post
}