package com.learning.backend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.backend.Dto.CategoryDto;
import com.learning.backend.Service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/addCategory")
	public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
		return categoryService.addcategory(categoryDto);
	}
	
	@PostMapping("/isCategoryDuplicate")
	public boolean isCategoryDuplicate(@RequestBody String category) {
		return categoryService.isCategoryDuplicate(category);
	}
	@GetMapping("/getAllCategorys")
	public List<CategoryDto> getAllCategorys(){
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		categoryDtoList = categoryService.getAllCategorys();
		return categoryDtoList;
	}
	@GetMapping("/getCategoryById/{id}")
	public CategoryDto getCategoryById(@PathVariable String id) {
		return categoryService.getCategoryById(id);
	}
	@PutMapping("/updateCategory/{id}")
	public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable String id) {
		return categoryService.updateCategory(categoryDto,id);
	}
	@DeleteMapping("/deleteCategoryById/{id}")
	public CategoryDto deleteCategoryById(@PathVariable String id) {
		return categoryService.deleteCategoryById(id);
	}
}
