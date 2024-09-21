package com.nagarro.services;

import java.util.List;

import com.nagarro.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategory();
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	void deleteCategory(Integer categoryId);
	
}
