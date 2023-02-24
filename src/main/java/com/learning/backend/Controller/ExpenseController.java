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


import com.learning.backend.Dto.ExpenseDto;
import com.learning.backend.Service.ExpenseService;

@CrossOrigin("*")
@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping("/addExpense/{userId}")
	public ExpenseDto addExpense(@RequestBody ExpenseDto expenseDto,@PathVariable String userId) {
		return expenseService.addExpense(expenseDto,userId);
	}
	@GetMapping("/getAllExpenses")
	public List<ExpenseDto> getAllExpenses(){
		List<ExpenseDto> expenseDtoList = new ArrayList<ExpenseDto>();
		expenseDtoList = expenseService.getAllExpenses();
		return expenseDtoList;
	}
	@PutMapping("/updateExpense/{id}")
	public ExpenseDto updateExpense(@RequestBody ExpenseDto expenseDto,@PathVariable String id) {
		return expenseService.updateExpense(expenseDto,id);
	}
	@DeleteMapping("/deleteExpense/{id}")
	public ExpenseDto deleteExpence(@PathVariable String id) {
		return expenseService.deleteExpense(id);
	}
	@GetMapping("getExpenseById/{id}")
	public ExpenseDto getExpenseById(@PathVariable String id) {
		return expenseService.getExpenseById(id);
	}
}
