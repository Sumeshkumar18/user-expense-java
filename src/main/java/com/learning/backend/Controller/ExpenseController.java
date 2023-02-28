package com.learning.backend.Controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

	@Autowired
	private ExpenseService expenseService;
	
	@Value("${download.path}")
	private String downloadsPath;
	
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
	@PutMapping("/downloadExpenseDetails")
	public void downloadExpenseDetails (@RequestBody List<ExpenseDto> expenseDtoList,HttpServletResponse response) {
		
		BufferedInputStream inStream = null;
		BufferedOutputStream outStream = null;
		try {
			File expenseFile = new File(downloadsPath + "Userexpense_Expense _Details.xlsx");
			FileUtils.writeByteArrayToFile(expenseFile,
					expenseService.downloadExpenseDetails(expenseDtoList));
			
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + expenseFile.getName());
			response.setContentLength((int) expenseFile.length());
			
			inStream = new BufferedInputStream(new FileInputStream(expenseFile));
			outStream = new BufferedOutputStream(response.getOutputStream());
			
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			
			response.flushBuffer();
			expenseFile.deleteOnExit();
			
		} catch (final Exception e) {
			
			logger.error("cought at catch block");
			
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				logger.error("cought at finally block");
			}
		}
	}
	
}
