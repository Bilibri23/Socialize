package com.socialize.controller;

import com.socialize.dto.PostDTO;
import com.socialize.dto.UserDTO;
import com.socialize.model.Post;
import com.socialize.model.User;
import com.socialize.service.PostService;
import com.socialize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//creating url  end points for the PostService
@RestController
@RequestMapping("/api/PostService")
@CrossOrigin
public class PostController {
    //under each  url endpoint are functions  that performs the necessary actions
    //connecting the service and controller class through a reference to reference postserviceclass
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @PostMapping("/addpost")
    public ResponseEntity<?> addPost(@RequestBody Post post){
        UserDTO user = new UserDTO();
        Post savedPost = postService.savePost(user, post.getContent());
        return ResponseEntity.created(URI.create("/private/mypost")).body(savedPost);
    }
    /*
    a user does  not see  all the posts uploaded on a social media
    he instead sees the posts uploaded by his friends
     we need a reference id(userid) to see which post was uploaded by a  specific user

    */
    @GetMapping("/mypost")
    public  ResponseEntity<?> myPosts() throws NullPointerException {
        UserDTO user = userService.getUserById(9L);
        List<PostDTO> postList = postService.getPostOfUser(user.getId());
        return ResponseEntity.ok(postList);

    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList =postService.getAllPost();
        return ResponseEntity.ok(postList);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostofUser(@PathVariable Long userId){
        List<PostDTO> postList =postService.getPostOfUser(userId);
        return ResponseEntity.ok(postList);
    }
    //we incorporated the service class to controller class


}

