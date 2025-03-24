package com.p1.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.p1.entity.User;

public interface UserRepo extends CrudRepository<User, Integer>{
	
	Optional<User> findByName(String name);
	
	Optional<User> findByEmail(String email);

}
