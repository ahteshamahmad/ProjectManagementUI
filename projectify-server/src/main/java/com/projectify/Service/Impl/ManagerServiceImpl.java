package com.projectify.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectify.Constant.ExceptionConstant;
import com.projectify.Exception.ResourceNotFoundException;
import com.projectify.Model.Manager;
import com.projectify.Model.Project;
import com.projectify.Model.User;
import com.projectify.Repository.ManagerRepository;
import com.projectify.Repository.UserRepository;
import com.projectify.Service.ManagerService;
import com.projectify.Service.Repository.UserRepositoryService;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private UserRepositoryService userRepositoryService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public void addProject(Project project, String email) throws Exception {
		// TODO Auto-generated method stub
		User sessionUser = userRepositoryService.findByUserName(email);
		
		project.setManager(sessionUser.getManager());
		sessionUser.getManager().getProjects().add(project);
		
		userRepository.save(sessionUser);
	}

	@Override
	public List<User> getAllManagers() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<User> managerRelatedUsers = new ArrayList<>();
			List<Manager> allManagers = managerRepository.findAll();
			allManagers.forEach(manager -> {
				managerRelatedUsers.add(manager.getUser());
			});
			if (allManagers.isEmpty()) {
				throw new ResourceNotFoundException("No Manager Available");
			}
			return managerRelatedUsers;
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
