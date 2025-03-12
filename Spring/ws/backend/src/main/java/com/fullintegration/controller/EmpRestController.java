package com.fullintegration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullintegration.model.Emp;
import com.fullintegration.repo.EmpRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpRestController {
	@Autowired
	private EmpRepo repo;
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/employees")
	public List<Emp> getEmployee(){
		return repo.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/employees")
	public Emp insertEmployee(@RequestBody Emp e) {
		return repo.save(e);
	}
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@PutMapping("/employees")
	public Emp updateEmployee(@RequestBody Emp e) {
		return repo.save(e);
	}
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/employees/{id}")
	public Optional<Emp> getEmployeeById(@PathVariable int id) {
		return repo.findById(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200/")	
	@DeleteMapping("/employee/{id}")
	public Map<String, String> deleteEmployee(@PathVariable int id) {
		repo.deleteById(id);
		Map<String, String> res = new HashMap<>();
		res.put("message", "Deleted Successfully");
		return res;
	}
}
