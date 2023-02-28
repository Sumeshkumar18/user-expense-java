package com.learning.backend.Service;

import java.util.List;

import com.learning.backend.Dto.ExpenseDto;

public interface ExpenseService {

//	ExpenseDto addExpense(ExpenseDto expenseDto);

	List<ExpenseDto> getAllExpenses();

//	ExpenseDto updateExpense(ExpenseDto expenseDto);

	ExpenseDto deleteExpense(String id);

	ExpenseDto getExpenseById(String id);

	ExpenseDto addExpense(ExpenseDto expenseDto, String userId);

	ExpenseDto updateExpense(ExpenseDto expenseDto, String id);

	byte[] downloadExpenseDetails(List<ExpenseDto> expenseDtoList) throws Exception;


}
