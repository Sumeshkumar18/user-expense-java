package com.learning.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.backend.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

//	Object getCategory(String category);

	Object getCategoryByCategory(String category);

	Category findById(String id);






	

}
