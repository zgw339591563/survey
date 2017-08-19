package com.atguigu.survey.component.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.survey.entities.test.Employee;

public interface EmployeeMapper {
	
	void updateEmpName(@Param("empId") Integer empId, @Param("empName") String empName);

	List<Employee> selectAll();

}
