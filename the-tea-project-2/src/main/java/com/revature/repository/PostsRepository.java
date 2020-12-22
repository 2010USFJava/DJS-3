package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

}
