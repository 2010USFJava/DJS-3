package com.revatue.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revatue.models.Post;
import com.revature.repository.PostRepository;



@Service
public class PostService {
private PostRepository pRepo;
	
	@Autowired
	public PostService(PostRepository pRepo) {
		this.pRepo=pRepo;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<Post> getAll(){
		return pRepo.getAll();
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public Post getById(int id) {
		return pRepo.getById(id);
	}
	
	@Transactional
	public Post add(Post newPost) {
		if(newPost.getPost().equals("")|| newPost.getImage().equals("")) return null;
		if(newPost.getPost()==null|| newPost.getImage()==null) return null;
		return pRepo.add(newPost);
	}
	@Transactional
	public Post update(Post updatedPost ) {
		if(updatedPost.getPost().equals("")|| updatedPost.getImage().equals("")) return null;
		if(updatedPost.getPost()==null|| updatedPost.getImage()==null) return null;
		return pRepo.update(updatedPost);
	}
	
	@Transactional
	public boolean delete(int id) {
		return pRepo.delete(id);
	}

}
