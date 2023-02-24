package com.learning.backend.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.backend.Dto.WidgetDto;
import com.learning.backend.Entity.Expense;
import com.learning.backend.Repository.CategoryRepository;
import com.learning.backend.Repository.ExpenseRepository;
import com.learning.backend.Repository.UserRepository;
import com.learning.backend.Service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public List<WidgetDto> widgetData() {
		List<WidgetDto> resultDto = new ArrayList<>();

		int userCount = userRepository.findAll().size();
		int categoryCount = categoryRepository.findAll().size();
		int expenseCount = expenseRepository.findAll().size();

		int total = userCount + categoryCount + expenseCount;
		double userPercent = (userCount / (double) total) * 100;
		double categoryPercent = (categoryCount / (double) total) * 100;
		double expensePercent = (expenseCount / (double) total) * 100;

		WidgetDto user = new WidgetDto();
		user.setTitle("User count");
		user.setCount(Integer.parseInt("" + Math.round(userPercent)));

		WidgetDto category = new WidgetDto();
		category.setTitle("Category Count");
		category.setCount(Integer.parseInt("" + Math.round(categoryPercent)));

		WidgetDto expense = new WidgetDto();
		expense.setTitle("Expense Count");
		expense.setCount(Integer.parseInt("" + Math.round(expensePercent)));

		resultDto.add(user);
		resultDto.add(category);
		resultDto.add(expense);

		return resultDto;
	}

	@Override
	public List<Integer> getAllExpenseByUserYear(Long userId) {
		String year = String.valueOf(LocalDateTime.now().getYear());
		List<Expense> allExpense = new ArrayList<>();
		List<Expense> expenseList = expenseRepository.findExpensesByUserIdAndYear(userId, year);
		int jan = 0;
		int feb = 0;
		int mar = 0;
		int apr = 0;
		int may = 0;
		int jun = 0;
		int jul = 0;
		int aug = 0;
		int sep = 0;
		int oct = 0;
		int nov = 0;
		int dec = 0;
		for (Expense expense : expenseList) {
			allExpense.add(expense);
			if (Integer.parseInt(expense.getMonth()) == 1) { // jan
				jan += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 2) {
				feb += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 3) {
				mar += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 4) {
				apr += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 5) {
				may += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 6) {
				jun += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 7) {
				jul += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 8) {
				aug += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 9) {
				sep += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 10) {
				oct += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 11) {
				nov += expense.getAmount();
			}
			if (Integer.parseInt(expense.getMonth()) == 12) {
				dec += expense.getAmount();
			}

		}
		List<Integer> amount = Arrays.asList(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
		return amount;
	}

}
