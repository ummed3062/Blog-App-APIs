package com.nagarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
