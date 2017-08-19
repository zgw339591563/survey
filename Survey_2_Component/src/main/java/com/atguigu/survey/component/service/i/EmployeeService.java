package com.atguigu.survey.component.service.i;

import java.io.FileNotFoundException;
import java.util.List;

import com.atguigu.survey.entities.test.Employee;

public interface EmployeeService {
	
	void updateTwice() throws FileNotFoundException;

	List<Employee> getEmpList();

}
