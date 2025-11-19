package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dto.PostRequestDTO;
import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.entity.Post;

import java.util.List;

public interface PostService {
    PostResponseDTO createPost(int userId, PostRequestDTO post);
    PostResponseDTO getPostById(Integer id);
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO updatePost(Integer id, PostRequestDTO Post );
    void deletePost(Integer id);
    List<PostResponseDTO> getPostsByUser(Integer userId);
    List<PostResponseDTO> searchPosts(String keyword);
}
