package com.nagarro.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nagarro.entities.Category;
import com.nagarro.entities.Comment;
import com.nagarro.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private int postId;
	
	private String postTitle;
	
	private String postContent;
	
	private String postImage;
	
	private Date postAddedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
