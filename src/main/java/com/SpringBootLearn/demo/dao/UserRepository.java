package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Post;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<Post> findPostsById(Integer userId);
}
