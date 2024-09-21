package com.nagarro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagarro.entities.Category;
import com.nagarro.entities.Post;
import com.nagarro.entities.User;
import com.nagarro.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.postTitle like :key")
    List<Post> searchByTitle(@Param("key") String postTitle);

}
