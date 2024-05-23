package com.socialize.repository;

import com.socialize.dto.UserDTO;
import com.socialize.model.Post;
import com.socialize.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String user);
    User findUserById(Long id);

}
