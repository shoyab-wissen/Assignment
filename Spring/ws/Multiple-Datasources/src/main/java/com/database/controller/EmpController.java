package com.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.entities.Employee;
import com.database.repo.EmpRepo;
import com.database.repo2.EmpRepo2;

@RestController
public class EmpController {
	@Autowired
	EmpRepo repo;
	@Autowired
	EmpRepo2 repo2;
	@PostMapping("/add-employee")
	public String addEmployee(@RequestBody Employee emp) {
		repo.save(emp);
		repo2.save(emp);
		return "Employee added Successfully";
	}
}
