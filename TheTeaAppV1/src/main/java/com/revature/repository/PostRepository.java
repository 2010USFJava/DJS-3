package com.revature.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revatue.models.Post;

@Repository
public class PostRepository implements CrudRepository<Post> {
private SessionFactory factory;
	
	@Autowired
	public PostRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	@Override
	public List<Post> getAll() {
		return factory.getCurrentSession().createQuery("from Post", Post.class).getResultList();
	}
	@Override
	public Post getById(int id) {
		return factory.getCurrentSession().get(Post.class,  id);
	}
	@Override
	public Post add(Post newPost) {
		factory.getCurrentSession().save(newPost);
		return newPost;
	}
	@Override
	public Post update(Post updatedPost) {
		Session session = factory.getCurrentSession();
		Post post = session.get(Post.class, updatedPost.getPostId());
		if(post==null) return null;
		post.setPost(updatedPost.getPost());
		post.setImage(updatedPost.getImage());
		return post;
	}
	@Override
	public boolean delete(int id) {
		Session session = factory.getCurrentSession();
		Post post = session.get(Post.class,  id);
		if(post == null) return false;
		session.delete(post);
		return true;
	}
}
