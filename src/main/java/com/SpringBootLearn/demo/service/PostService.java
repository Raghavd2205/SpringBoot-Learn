package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(Integer userId, Post post);
    Post getPostById(Integer id);
    List<Post> getAllPosts();
    Post updatePost(Integer id, Post postDetails);
    void deletePost(Integer id);
    List<Post> getPostsByUser(Integer userId);
    List<Post> searchPosts(String keyword);
}
