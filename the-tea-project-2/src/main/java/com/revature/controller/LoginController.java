package com.revature.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.models.Users;
import com.revature.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/thetea")
public class LoginController {
	@Autowired
	private UsersRepository userRepository;
	
	@GetMapping("/login")
	private List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping("/login")
	public Users login(@Valid @RequestBody String username, String password) throws ResourceNotFoundException {
		List<Users> userList = getAllUsers();
		Users user = new Users();
		Iterator<Users> iterator = userList.iterator();
		while(iterator.hasNext()) {
			user = iterator.next();
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
				return user;
			} else {
				throw new ResourceNotFoundException("Login incorrect");
			}
		}
		return null;
	}
}
