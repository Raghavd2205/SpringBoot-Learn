package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.User;

import java.util.List;

public interface UserService {
    List<Post> findPostsById(Integer userId);
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    User updateUser(int id, User userDetails);

    void deleteUser(int id);

}
