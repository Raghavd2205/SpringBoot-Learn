package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
