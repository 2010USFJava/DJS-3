package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "likes", schema = "public")
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="likes_id", nullable=false)
	private int likesId;
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "likes", nullable = false)
	private int likes;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.ALL})
	@JoinColumn(name = "postId", nullable = false)
	private Posts post;
	
}
