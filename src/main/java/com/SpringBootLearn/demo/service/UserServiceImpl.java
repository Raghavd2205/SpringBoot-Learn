package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository  userRepository;
    @Override
    public List<Post> findPostsById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id " + userId));
        return user.getPosts();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id " + id));
    }

    @Override
    public User updateUser(int id, User userDetails) {
       User user = getUserById(id);
       user.setName(userDetails.getName());
       user.setEmail(userDetails.getEmail());
       user.setPosts(userDetails.getPosts());
        return user;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
