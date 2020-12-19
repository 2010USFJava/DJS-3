package com.revature.repository;
import java.util.List;

public interface CrudRepository<T>{
		List<T> getAll();
		T getById(int id);
		T add(T newobject);
		T update(T updatedObject);
		boolean delete(int id);
	}


