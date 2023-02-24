package com.learning.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.backend.Entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

//	@Query("SELECT e FROM Expense e WHERE e.userId = ?1")//SELECT u FROM User u WHERE u.age = ?1
//	List<Expense> findExpensesByUserId(Long userId);

	List<Expense> findAllAmountByUserId(Long userId);

	List<Expense> findExpensesByUserIdAndYear(Long userId, String year);

//	List<Expense> findCreatedDateByYear(Long userId);
}
