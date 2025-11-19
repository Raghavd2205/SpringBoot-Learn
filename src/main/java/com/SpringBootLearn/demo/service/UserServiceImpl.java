package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.dto.PostResponseDTO;
import com.SpringBootLearn.demo.dto.UserRequestDTO;
import com.SpringBootLearn.demo.dto.UserResponseDTO;
import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        User saved = userRepository.save(user);
        return toUserDTO(saved);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return toUserDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(int id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        User updated = userRepository.save(user);
        return toUserDTO(updated);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO toUserDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPostCount(user.getPosts().size());
        return dto;
    }
    @Override
    public List<PostResponseDTO> findPostsById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        return user.getPosts()
                .stream()
                .map(this::toPostDTO)
                .collect(Collectors.toList());
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
