package com.nagarro.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entities.Comment;
import com.nagarro.entities.Post;
import com.nagarro.entities.User;
import com.nagarro.exceptions.ResourceNotFoundException;
import com.nagarro.payloads.ApiResponse;
import com.nagarro.payloads.CommentDto;
import com.nagarro.repositories.CommentRepo;
import com.nagarro.repositories.PostRepo;
import com.nagarro.repositories.UserRepo;
import com.nagarro.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {

		CommentDto commentDto2 = null;
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = user.getPosts();
		
		for (Post post : posts) {
			if(post.getPostId().equals(postId)) {
				Comment comment = this.modelMapper.map(commentDto, Comment.class);

				comment.setPost(post);
				comment.setUser(user);
				Comment savedComment = this.commentRepo.save(comment);
				commentDto2 = this.modelMapper.map(savedComment, CommentDto.class);
			}
		}
		
//		Post post = this.postRepo.findById(postId)
//				.orElseThrow(() -> new ResourceNotFoundException("post", "Post Id", postId));
		
		return commentDto2;
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

		this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

		comment.setContent(commentDto.getContent());
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

}
