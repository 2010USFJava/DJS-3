package com.revatue.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revatue.models.User;
import com.revature.repository.UserRepository;

@Service
public class UserService {
	private UserRepository uRepo;
	
	@Autowired
	public UserService(UserRepository uRepo) {
		this.uRepo=uRepo;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<User> getAll(){
		return uRepo.getAll();
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public User getById(int id) {
		return uRepo.getById(id);
	}
	
	@Transactional
	public User add(User newUser) {
		if(newUser.getFirstName().equals("")|| newUser.getLastName().equals("")) return null;
		if(newUser.getFirstName()==null|| newUser.getLastName()==null) return null;
		return uRepo.add(newUser);
	}
	@Transactional
	public User update(User updatedUser ) {
		if(updatedUser.getFirstName().equals("")|| updatedUser.getLastName().equals("")) return null;
		if(updatedUser.getFirstName()==null|| updatedUser.getLastName()==null) return null;
		return uRepo.update(updatedUser);
	}
	
	@Transactional
	public boolean delete(int id) {
		return uRepo.delete(id);
	}

}
