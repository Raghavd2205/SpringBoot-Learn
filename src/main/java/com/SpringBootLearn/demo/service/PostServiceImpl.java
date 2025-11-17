package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.PostRepository;
import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    private UserRepository userRepository;
    @Override
    public Post createPost(Integer userId, Post post) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public Post updatePost(Integer id, Post postDetails) {
        return null;
    }

    @Override
    public void deletePost(Integer id) {

    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return List.of();
    }
  @Override
    public List<Post> searchPosts(String keyword) {
        return List.of();
    }
}
