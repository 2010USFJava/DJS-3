package com.revature.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.repository.UsersRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/thetea")
public class UsersController {
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/users")
	public List<User> getAllEmployees() {
		return usersRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") int userId) {
		Optional<User> users = usersRepository.findById(userId);
		return users;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name){
		try {
			List<User> users = new ArrayList<User>();
			if(name == null)
				usersRepository.findAll().forEach(users::add);
			else
				usersRepository.findByName(name).forEach(users::add);
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{firstName}" , produces=MediaType.APPLICATION_JSON_VALUE) 
	public List<User> findByFirstName(String firstName) {
		return (List<User>) usersRepository.findByName(firstName);

	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public User createUser(@Valid @RequestBody User user) {
		return usersRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,	@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		final User updatedUser = usersRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException {
		User user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		usersRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
