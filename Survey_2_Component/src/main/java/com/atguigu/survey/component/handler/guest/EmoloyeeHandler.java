package com.atguigu.survey.component.handler.guest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.survey.component.service.i.EmployeeService;
import com.atguigu.survey.entities.test.Employee;

@Controller
public class EmoloyeeHandler {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/guest/employee/showList")
	public String showList(Map<String, Object> map) {
		
		List<Employee> empList = employeeService.getEmpList();
		map.put("empList", empList);
		
		return "guest/emp_list";
	}

}
