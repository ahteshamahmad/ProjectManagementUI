package com.projectify.Service;

import java.util.List;

import com.projectify.Model.Project;
import com.projectify.Model.User;

public interface ManagerService {
	
	public void addProject(Project project, String email) throws Exception;
	
	public List<User> getAllManagers() throws Exception;
	
}
