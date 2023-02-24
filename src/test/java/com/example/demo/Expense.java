/**
 * 
 */
package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.learning.backend.Entity.Expense;
import com.learning.backend.Repository.ExpenseRepository;
import com.learning.backend.Service.ExpenseService;

/**
 * @author Promantus
 *
 */

@SpringBootTest
class ExpenseController {

	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private ExpenseService expenseSer;

	@Test
	void test() {
		System.err.println("Test case ran");
	}

	@Test
	public void getAllExpenses() {


	}
}
