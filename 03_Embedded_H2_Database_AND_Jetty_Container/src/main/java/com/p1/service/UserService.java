package com.p1.service;

import com.p1.entity.User;

public interface UserService {
	
	public void RegisterUser(User user);
	
	public User findByName(String name);
	
	public User findByEmail(String email);
	
	public boolean loginUser(String name, String pwd);

}
