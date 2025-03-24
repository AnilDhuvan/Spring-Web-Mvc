package com.p1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
