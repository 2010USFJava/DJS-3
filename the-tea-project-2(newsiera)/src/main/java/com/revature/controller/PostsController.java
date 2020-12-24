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
import com.revature.models.User;
import com.revature.repository.PostsRepository;
import com.revature.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/theteaPost")
public class PostsController {
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UsersController usersController;
	
	//@GetMapping("/posts")
	//@Query(value = "select * from posts")
	@GetMapping("/posts")
	public List<Posts> getAllPosts() {
		return postsRepository.findAll();
	}
	
//	@PutMapping()
//	public Posts putPost() {
//		return new Posts();
//	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/posts/{id}")
	public Optional<Posts> getPostById(@PathVariable(value = "id") int postId) {
		Optional<Posts> posts = postsRepository.findById(postId);
		return posts;
	}
	
	//changing method name to createPost instead of createUser
//	//@ResponseStatus(HttpStatus.CREATED)
//	@PostMapping("/addPost/{user_id}")
//	public Posts createPost(@PathVariable(value = "user_id")int userId,@Valid @RequestBody Posts post) {
//		System.out.println("POST IS HERE " + 
//	post);
//		return postsRepository.save(post);
//	}
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addPost/{userId}")
	public Posts createPost(@PathVariable(value="userId") int userId, @Valid @RequestBody Posts post) {
		System.out.println("POST IS HERE " + 
	post);
		User user=usersRepository.getOne(userId);
		post.setUser(user);
		return postsRepository.save(post);
	}
	
	//This works
//	@ResponseStatus(HttpStatus.CREATED)
//	@PostMapping("/addPost")
//	public Posts createPost(@Valid @RequestBody Posts post) {
//		System.out.println("POST IS HERE " + 
//	post);
//		return postsRepository.save(post);
//	}

	
	@PutMapping("/updatePosts/{id}")
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
