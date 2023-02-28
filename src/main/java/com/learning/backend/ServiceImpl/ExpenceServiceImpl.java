package com.learning.backend.ServiceImpl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.learning.backend.UserExpenseDefaultMethods;
import com.learning.backend.Dto.ExpenseDto;
import com.learning.backend.Entity.Category;
import com.learning.backend.Entity.Expense;
import com.learning.backend.Entity.User;
import com.learning.backend.Repository.CategoryRepository;
import com.learning.backend.Repository.ExpenseRepository;
import com.learning.backend.Repository.UserRepository;
import com.learning.backend.Service.ExpenseService;


@Service
public class ExpenceServiceImpl implements ExpenseService{
	
	private static final Logger logger = LoggerFactory.getLogger(ExpenceServiceImpl.class);
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ResourceLoader resourceLoader;
	
	@Override
	public ExpenseDto addExpense(ExpenseDto expenseDto,String userId) {
		
		ExpenseDto resultDto = new ExpenseDto();
		
		Expense expense = new Expense();
		expense.setUserId(Long.parseLong(userId));
		expense.setCategoryId(Long.parseLong(expenseDto.getCategoryId()));
		expense.setExpDate(expenseDto.getExpDate());
		expense.setAmount(expenseDto.getAmount());
		expense.setDescription(expenseDto.getDescription());
//		expense.setCreatedBy(expenseDto.getCreatedBy());
		expense.setCreatedDate(LocalDateTime.now());
		
		String year = String.valueOf(LocalDateTime.now().getYear());
		expense.setYear(year);
		String month = String.valueOf(LocalDateTime.now().getMonthValue());
		expense.setMonth(month);
		
		expenseRepository.save(expense);
		resultDto.setStatus(1);
		resultDto.setMessage("Expense added");
		return resultDto;
				
	}

	@Override
	public List<ExpenseDto> getAllExpenses() {
		List<ExpenseDto> expenseDtoList = new ArrayList<ExpenseDto>();
		List<Expense> expensesList = expenseRepository.findAll();
		for (Expense expense:expensesList) {
			expenseDtoList.add(this.getExpenseDto(expense));
		}
		return expenseDtoList;
	}

	private ExpenseDto getExpenseDto(Expense expense) {
		
		ExpenseDto expenseDto = new ExpenseDto();
		
		expenseDto.setId(expense.getId());
		expenseDto.setUserId(expenseDto.getUserId());
		expenseDto.setCategoryId(expenseDto.getCategoryId());
		expenseDto.setExpDate(expense.getExpDate());
		expenseDto.setAmount(expense.getAmount());
		expenseDto.setDescription(expense.getDescription());
		
		Category category = categoryRepository.findById(expense.getCategoryId()).orElse(null);
		expenseDto.setCategory(category.getCategory());
		User user = userRepository.findById(expense.getUserId()).orElse(null);
		expenseDto.setName(user.getName());
		
		expenseDto.setYear(expense.getYear());
		expenseDto.setMonth(expense.getMonth());
		return expenseDto;
	}

	@Override
	public ExpenseDto updateExpense(ExpenseDto expenseDto,String id) {
		
		ExpenseDto resultDto = new ExpenseDto();
//		Expense expense = expenseRepository.findById(expenseDto.getId()).orElse(null);
		Expense expense = expenseRepository.findById(Long.parseLong(id)).orElse(null);
		
//		if(expense == null) {
//			resultDto.setStatus(0);
//			resultDto.setMessage("expense not found");
//			return resultDto;
//		}
//		expense.setUserId(Long.parseLong(expenseDto.getUserId()));
//		expense.setCategoryId(Long.parseLong(expenseDto.getCategoryId()));
		expense.setExpDate(expenseDto.getExpDate());
		expense.setAmount(expenseDto.getAmount());
		expense.setDescription(expenseDto.getDescription());
		expense.setUpdatedBy(expenseDto.getUpdatedBy());
		expense.setUpdatedDate(LocalDateTime.now());
		
		String year = String.valueOf(LocalDateTime.now().getYear());
		expense.setYear(year);
		String month = String.valueOf(LocalDateTime.now().getMonthValue());
		expense.setMonth(month);
		
		expenseRepository.save(expense);
		resultDto.setStatus(1);
		resultDto.setMessage("expense updated");
		return resultDto;
	}

	@Override
	public ExpenseDto deleteExpense(String id) {
		
		ExpenseDto resultDto = new ExpenseDto();
		Expense expense = expenseRepository.findById(Long.parseLong(id)).orElse(null);
		
		if (expense == null) {
			resultDto.setStatus(0);
			resultDto.setMessage("expense not available");
			return resultDto;
			
		}
		expenseRepository.deleteById(Long.parseLong(id));
		resultDto.setStatus(0);
		resultDto.setMessage("expense deleted");
		return resultDto;
	}

	@Override
	public ExpenseDto getExpenseById(String id) {
		Expense expense = expenseRepository.findById(Long.parseLong(id)).orElse(null);
		return expense != null ? this.getExpenseDto(expense) : new ExpenseDto();
	}

	@Override
	public byte[] downloadExpenseDetails(List<ExpenseDto> expenseDtoList) throws Exception {
		
		File file = resourceLoader.getResource("classpath:excel-templates/expense_details.xlsx").getFile();
		try  (Workbook expenseDetailsWB = new XSSFWorkbook(file)) {
			
			Sheet sheet = expenseDetailsWB.getSheetAt(0);
			UserExpenseDefaultMethods.cleanSheet(sheet);
			int rowNum = 2;
			for (ExpenseDto expenseDto : expenseDtoList) {
				
				Row dataRow = sheet.createRow(rowNum);
				
				Cell slNo = dataRow.createCell(0);
				slNo.setCellValue(rowNum - 1);
				
				dataRow.createCell(1).setCellValue(expenseDto.getExpDate().toString());
				dataRow.createCell(2).setCellValue(expenseDto.getCategory());
				dataRow.createCell(3).setCellValue(expenseDto.getAmount());
				dataRow.createCell(4).setCellValue(expenseDto.getDescription());
				dataRow.createCell(5).setCellValue("");
				
				rowNum++;
			}
			
			ByteArrayOutputStream outputSream = new ByteArrayOutputStream();
			expenseDetailsWB.write(outputSream);
			
			expenseDetailsWB.close();
			
			return outputSream.toByteArray();
		} catch (Exception ex) {
			
			logger.error("Error during Expense Details download file");
			return null;
		}	
	}

}
