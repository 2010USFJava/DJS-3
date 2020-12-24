package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.Posts;

@Repository

public interface PostsRepository extends JpaRepository<Posts, Integer> {
	
	@Query(value = "SELECT * FROM posts WHERE user_id=:userId", nativeQuery = true)
	public List<Posts> findByUserId(@Param("userId") int userId);
}
