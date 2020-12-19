package com.revatue.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "posts")
public class Post {

	@Id
	@Column
	@GeneratedValue
	private int post_id;
	private int user_id;
	private String post;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date timeStamp;
	private String image;
	


	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Post(int post_id, int user_id, String post, Date timeStamp, String image) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.post = post;
		this.timeStamp = timeStamp;
		this.image = image;
	}


	public int getPostId() {
		return post_id;
	}

	public void setPostId(int post_id) {
		this.post_id = post_id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", user_id=" + user_id + ", post=" + post + ", timeStamp=" + timeStamp
				+ ", image=" + image + "]";
	}
	

}