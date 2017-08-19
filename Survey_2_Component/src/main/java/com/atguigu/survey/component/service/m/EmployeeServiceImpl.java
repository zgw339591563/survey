package com.atguigu.survey.component.service.m;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.EmployeeMapper;
import com.atguigu.survey.component.service.i.EmployeeService;
import com.atguigu.survey.entities.test.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public void updateTwice() throws FileNotFoundException {
		
		employeeMapper.updateEmpName(1, "new01~~~");
		
		//System.out.println(10 / 0);
		new FileInputStream("abc.txt");
		
		employeeMapper.updateEmpName(2, "new02~~~");
		
	}

	@Override
	public List<Employee> getEmpList() {
		return employeeMapper.selectAll();
	}

}
