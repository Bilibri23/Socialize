package com.socialize.service;

import com.socialize.dto.PostDTO;
import com.socialize.dto.UserDTO;
import com.socialize.model.Post;
import com.socialize.model.User;
import com.socialize.repository.PostRepository;
import com.socialize.repository.UserRepository;
import com.socialize.service.mapperService.MapperService;
import com.socialize.service.mapperService.PostMapperService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;


//contains the business logic of the post service
/*
defining 3 functions that contains
the business logic of the rest api requests defined in  controller class
1 saving the data to the database
2  retrieving all the data from the database
3 deleting the post from the database
*/

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository; //reference from the repositoryInterface to use its overrided method
    //in the service class
    @Autowired
    private UserRepository userRepository;


    private MapperService<Post, PostDTO> postMapper;

    @Autowired
    private MapperService<User, UserDTO> userMapper;

    public Post savePost(UserDTO userDTO, String content){

        //initialising some values as we send our post data
      Post post = new Post();
      User user = userRepository.findUserByEmail(userDTO.getEmail());

      post.setUser(user);
      post.setContent(content);
      return postRepository.save(post);

    }
    public List<PostDTO> getPostOfUser(Long userId){
          List<Post> postList = postRepository.findPostByUserOrderById(userRepository.findUserById(userId));
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post : postList){
            postDTOList.add(postMapper.mapToDTO(post));
        }
        return postDTOList;
    }
    public List<Post> getAllPost(){
        return postRepository.findAllByOrderByIdDesc();

    }
}
