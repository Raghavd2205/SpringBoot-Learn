package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.PostRepository;
import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.dto.PostRequestDTO;
import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public PostResponseDTO createPost(int userId, PostRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);

        Post saved = postRepository.save(post);

        return toPostDTO(saved);
    }


    @Override
    public PostResponseDTO getPostById(Integer id) {
         Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return toPostDTO(post);
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::toPostDTO)
                .collect(Collectors.toList());

    }

    @Override
    public PostResponseDTO updatePost(Integer id, PostRequestDTO postDetails) {
        return null;
    }

    @Override
    public void deletePost(Integer id) {

    }

    @Override
    public List<PostResponseDTO> getPostsByUser(Integer userId) {
        return List.of();
    }
  @Override
    public List<PostResponseDTO> searchPosts(String keyword) {
        return List.of();
    }
    private PostResponseDTO toPostDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUserId(post.getUser().getId());
        return dto;
    }


}
