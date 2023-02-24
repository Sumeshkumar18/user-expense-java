package com.learning.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.backend.Dto.WidgetDto;
import com.learning.backend.Service.DashboardService;

@CrossOrigin("*")
@RestController
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@GetMapping("/widgetData")
	public List<WidgetDto> widgetData() {
		return dashboardService.widgetData();

	}

	@GetMapping("getAllExpenseByUserYear/{userId}")
	public List<Integer> getAllExpenseByUserYear(@PathVariable Long userId) {
		return dashboardService.getAllExpenseByUserYear(userId);
	}

}
