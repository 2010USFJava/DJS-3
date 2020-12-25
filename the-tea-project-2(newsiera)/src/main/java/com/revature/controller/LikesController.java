package com.revature.controller;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.repository.LikesRepository;
import com.revature.repository.PostsRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/theteaPost")
public class LikesController {
	@Autowired
	private LikesRepository likesRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
//	@PostMapping("/addLikes/{postId}")
//	public Likes addLikes(@PathVariable(value = "postId") int postId) {
//		return likesRepository.addLikes(postId);
//		//Posts post=postsRepository.getOne(postId);
//		//dummy.setPost(post);
//		//return likesRepository.save(dummy);
//	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/likes/{postId}")
	public void getLikesById(@PathVariable(value = "postId") int postId) throws GenericJDBCException{
		int maxLikes = likesRepository.getLikes(postId);
		System.out.println("in getLikesById " + maxLikes);
		//return maxLikes;
	}

}
