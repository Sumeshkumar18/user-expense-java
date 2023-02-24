package com.learning.backend.Service;

import java.util.List;

import com.learning.backend.Dto.WidgetDto;

public interface DashboardService {

	List<WidgetDto> widgetData();

	List<Integer> getAllExpenseByUserYear(Long userId);
	
}
