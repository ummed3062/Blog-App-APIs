package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
