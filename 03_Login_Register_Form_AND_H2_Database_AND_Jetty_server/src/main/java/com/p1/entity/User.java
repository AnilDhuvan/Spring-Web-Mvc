package com.p1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name="Users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="name is required")
	private String name;
	
	@Email(message="email should be valid")
	@NotBlank(message="email is required")
	private String email;
	
	@NotBlank(message = "pwd is required")
	private String pwd;
	
	

}
