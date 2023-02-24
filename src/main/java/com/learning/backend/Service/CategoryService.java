package com.learning.backend.Service;

import java.util.List;

import com.learning.backend.Dto.CategoryDto;

public interface CategoryService {

	CategoryDto addcategory(CategoryDto categoryDto);

	boolean isCategoryDuplicate(String category);

	List<CategoryDto> getAllCategorys();

	CategoryDto getCategoryById(String id);

//	CategoryDto updateCategory(CategoryDto categoryDto);

	CategoryDto deleteCategoryById(String id);

	CategoryDto updateCategory(CategoryDto categoryDto, String id);

	
	

}
