package com.revature.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revatue.models.User;

@Repository
public class UserRepository implements CrudRepository<User> {
private SessionFactory factory;
	
	@Autowired
	 public UserRepository(SessionFactory sessionFactory) {
		this.factory= sessionFactory;
	}
	
	@Override
	public List<User> getAll() {
		return factory.getCurrentSession().createQuery("from User", User.class).getResultList();
	}
	@Override
	public User getById(int id) {
		return factory.getCurrentSession().get(User.class, id);
	}
	@Override
	public User add(User newUser) {
		factory.getCurrentSession().save(newUser);
		return newUser;
	}
	
	@Override
	public User update(User updatedUser) {
		Session session =factory.getCurrentSession();
		User user= session.get(User.class,updatedUser.getUserId());
		if(user==null) return null;
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());		
		return user;
	}

	public boolean delete(int id) {
		Session session =factory.getCurrentSession();
		User card= session.get(User.class,id);
		if(card==null) return false;
		session.delete(card);
		return true;
	}
}