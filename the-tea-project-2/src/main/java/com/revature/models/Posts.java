package com.revature.models;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "posts", schema = "public") //@Data @AllArgsConstructor @NoArgsConstructor @ToString(exclude = {"users"})
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_post_id", nullable = false)
	private int postId;
	@Column(name = "user_post")
	private String userPost;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REFRESH})
	@JsonBackReference
	//@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private Users user;
	
	private String image;
//	private List<Users> likes; attached @Table to likes table???

	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Posts(String userPost, String image) { //int userId,
		super();
		this.userPost = userPost;
		//this.userId = userId;
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

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", userPost=" + userPost + ", image=" + image + "]";
	} //+ ", userId=" + userId
	
	
	
}
