package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.revature.models.Likes;


public interface LikesRepository extends JpaRepository<Likes, Integer>{
		
	@Query(value = "UPDATE likes SET likes=likes+1 WHERE user_post_id=:postId", nativeQuery = true)
	public int getLikes(@Param("postId") int postId);

}