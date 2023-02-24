package com.learning.backend.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.backend.Dto.CategoryDto;
import com.learning.backend.Entity.Category;
import com.learning.backend.Repository.CategoryRepository;
import com.learning.backend.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public CategoryDto addcategory(CategoryDto categoryDto) {
		
		CategoryDto resultDto = new CategoryDto();
//		if (this.isCategoryDuplicate(categoryDto.getCategory())) {
//			resultDto.setStatus(0);
//			resultDto.setMessage("already exist");
//		}
		Category category = new Category();
		category.setCategory(categoryDto.getCategory());
		category.setCreatedBy(categoryDto.getCreatedBy());
		category.setCreatedDate(LocalDateTime.now());;
		category.setUpdatedBy(categoryDto.getUpdatedBy());
		category.setUpdatedDate(LocalDateTime.now());
		categoryRepository.save(category);
		
		resultDto.setStatus(1);
		resultDto.setMessage("category added");
		return resultDto;
		
	}

	@Override
	public boolean isCategoryDuplicate(String category) {

		if(categoryRepository.getCategoryByCategory(category) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<CategoryDto> getAllCategorys() {
		
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		
		List<Category> categorysList = categoryRepository.findAll();
		for (Category category:categorysList) {
			categoryDtoList.add(this.getCategoryDto(category));
		}
		return categoryDtoList;
	}

	private CategoryDto getCategoryDto(Category category) {
		
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.setId(category.getId());
		categoryDto.setCategory(category.getCategory());
		categoryDto.setCreatedBy(category.getCreatedBy());
		categoryDto.setCreatedDate(category.getCreatedDate());
		categoryDto.setUpdatedBy(category.getUpdatedBy());
		categoryDto.setUpdatedDate(category.getUpdatedDate());
		
		return categoryDto;
	}

	@Override
	public CategoryDto getCategoryById(String id) {
		
		Category category = categoryRepository.findById(Long.parseLong(id)).orElse(null);
		
		return category != null ? this.getCategoryDto(category) : new CategoryDto();
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,String id) {
		
		CategoryDto resultDto = new CategoryDto();
//		Category category = categoryRepository.findById(categoryDto.getId()).orElse(null);
		Category category = categoryRepository.findById(Long.parseLong(id)).orElse(null);
		
//		if(category == null) {
//			resultDto.setMessage("category not available");
//			resultDto.setStatus(0);
//			return resultDto;
//		}
		category.setCategory(categoryDto.getCategory());
		category.setUpdatedBy(categoryDto.getUpdatedBy());
		category.setUpdatedDate(LocalDateTime.now());
		
		categoryRepository.save(category);
		resultDto.setStatus(1);
		resultDto.setMessage("category updated");
		return resultDto;
	}

	@Override
	public CategoryDto deleteCategoryById(String id) {
	
		CategoryDto resultDto = new CategoryDto();
		Category category = categoryRepository.findById(Long.parseLong(id)).orElse(null);
		
		if(category == null) {
			resultDto.setStatus(1);
			resultDto.setMessage("Category Not Available");
			return resultDto;
		}
		categoryRepository.deleteById(Long.parseLong(id));
		resultDto.setStatus(1);
		resultDto.setMessage("Category deleted");
		return resultDto;
	}

	

}
