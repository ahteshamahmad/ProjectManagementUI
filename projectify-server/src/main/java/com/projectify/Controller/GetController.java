package com.projectify.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectify.Constant.MessageConstant;
import com.projectify.Model.User;
import com.projectify.Service.EmployeeService;
import com.projectify.Service.ManagerService;

@RestController
@CrossOrigin("*")
public class GetController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ManagerService managerService;
	
	@GetMapping("/get-all-employees")
	public ResponseEntity<Map<String, Object>> getAllEmployees() throws Exception {
		List<User> allUserRelatedEmployees = employeeService.getAllEmployees();
		
		Map<String, Object> getAllEmployess = new HashMap<>();
		getAllEmployess.put("allUserRelatedEmployees", allUserRelatedEmployees);
		getAllEmployess.put("message", MessageConstant.GET_ALL_EMPLOYEES_SUCCESS);
		
		return new ResponseEntity<Map<String, Object>>(getAllEmployess, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-managers")
	public ResponseEntity<Map<String, Object>> getAllManagers() throws Exception {
		List<User> allUserRelatedManagers = managerService.getAllManagers();
		
		Map<String, Object> getAllEmployess = new HashMap<>();
		getAllEmployess.put("allUserRelatedManagers", allUserRelatedManagers);
		getAllEmployess.put("message", MessageConstant.GET_ALL_MANAGERS_SUCCESS);
		
		return new ResponseEntity<Map<String, Object>>(getAllEmployess, HttpStatus.OK);
	}
}
