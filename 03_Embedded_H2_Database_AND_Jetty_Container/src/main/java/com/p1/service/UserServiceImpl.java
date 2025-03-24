package com.p1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p1.entity.User;
import com.p1.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo repo;
	

	
	public void RegisterUser(User user) {
		repo.save(user);
	}

	
	public User findByName(String name) {
		return repo.findByName(name).orElse(null);
		
	}
	
	public User findByEmail(String email) {
		return repo.findByEmail(email).orElse(null);
	}

	
	public boolean loginUser(String name, String pwd) {
	   User user = findByName(name);
		return user != null && user.getPwd().equals(pwd);
			}


	

}
