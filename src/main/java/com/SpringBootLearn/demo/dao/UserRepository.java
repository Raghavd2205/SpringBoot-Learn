package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<Post> findPostsById(Integer userId);
    Optional<User> findByEmail(String email);
}
