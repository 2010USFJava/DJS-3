package com.revature.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.models.Users;
import com.revature.repository.UsersRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/thetea")
public class UsersController {
	@Autowired
	private UsersRepository usersRepository;
	
	
	@GetMapping("/users")
	public List<Users> getAllEmployees() {
		return usersRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/users/{id}")
	public Optional<Users> getUserById(@PathVariable(value = "id") int userId) {
		Optional<Users> users = usersRepository.findById(userId);
		return users;
	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public Users createUser(@Valid @RequestBody Users user) {
		return usersRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable(value = "id") int userId,	@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		final Users updatedUser = usersRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException {
		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		usersRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
