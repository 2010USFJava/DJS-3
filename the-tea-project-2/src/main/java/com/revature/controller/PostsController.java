package com.revature.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.models.Posts;
import com.revature.repository.PostsRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	private PostsRepository postsRepository;
	
	@GetMapping("/posts")
	public List<Posts> getAllEmployees() {
		return postsRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/posts/{id}")
	public Optional<Posts> getUserById(@PathVariable(value = "id") int postId) {
		Optional<Posts> posts = postsRepository.findById(postId);
		return posts;
	}
	
	//changing method name to createPost instead of createUser
	@PostMapping("/posts")
	public Posts createPost(@Valid @RequestBody Posts post) {
		return postsRepository.save(post);
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Posts> updatePost(@PathVariable(value = "id") int postId,	@Valid @RequestBody Posts postDetails) throws ResourceNotFoundException {
		Posts post = postsRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + postId));

		post.setUserPost(postDetails.getUserPost());
		post.setImage(postDetails.getImage());
		final Posts updatedPost = postsRepository.save(post);
		return ResponseEntity.ok(updatedPost);
	}

	@DeleteMapping("/posts/{id}")
	public Map<String, Boolean> deletePost(@PathVariable(value = "id") int postId)
			throws ResourceNotFoundException {
		Posts post = postsRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + postId));

		postsRepository.delete(post);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
