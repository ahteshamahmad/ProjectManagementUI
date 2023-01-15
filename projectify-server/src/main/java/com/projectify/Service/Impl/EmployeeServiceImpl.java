package com.projectify.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectify.Constant.ExceptionConstant;
import com.projectify.Exception.ResourceNotFoundException;
import com.projectify.Model.Employee;
import com.projectify.Model.User;
import com.projectify.Repository.EmployeeRepository;
import com.projectify.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<User> getAllEmployees() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<User> employeeRelatedUsers = new ArrayList<>();
			List<Employee> allEmployees = employeeRepository.findAll();
			allEmployees.forEach(employee -> {
				employeeRelatedUsers.add(employee.getUser());
			});
			if (allEmployees.isEmpty()) {
				throw new ResourceNotFoundException("No Employee Available");
			}
			return employeeRelatedUsers;
		} catch (ResourceNotFoundException e) {
			// TODO: handle exception
			System.out.println("ERROR -> " + e.getMessage());
			e.printStackTrace();
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR -> " + e.getMessage());
			e.printStackTrace();
			throw new Exception(ExceptionConstant.DEFAULT);
		}
	}

}
