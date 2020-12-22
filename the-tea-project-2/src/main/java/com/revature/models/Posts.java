package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_post_id", nullable = false)
	private int postId;
	@Column(name = "user_post")
	private String userPost;
//	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", referencedColumnName = "user_id")
	private int userId;
	private String image;
//	private List<Users> likes; attached @Table to likes table???

	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Posts(String userPost, int userId, String image) {
		super();
		this.userPost = userPost;
		this.userId = userId;
		this.image = image;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", userPost=" + userPost + ", userId=" + userId + ", image=" + image + "]";
	}
	
	
	
}
