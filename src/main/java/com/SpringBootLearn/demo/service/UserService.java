package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.dto.UserRequestDTO;
import com.SpringBootLearn.demo.dto.UserResponseDTO;
import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.User;

import java.util.List;

public interface UserService {
    List<PostResponseDTO> findPostsById(Integer userId);
    UserResponseDTO createUser(UserRequestDTO user);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(int id);

    UserResponseDTO updateUser(int id, UserRequestDTO userDetails);

    void deleteUser(int id);

}
