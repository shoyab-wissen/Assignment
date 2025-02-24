package com.database.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.entity.Student;
import com.database.entity.Teacher;
import com.database.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@GetMapping("/students")
	public List<Student> fetchAllStudent(){
		return service.getAllStudents();
	}
	
	@GetMapping("/student/{rollNo}")
	public Student getStudent(@PathVariable int rollNo, String school) {
	 	return service.getStudentByRollNo(rollNo, school);
	}
	
	@GetMapping("/student/school")
	public List<Student> getStudentBySchool(String name){
		return service.getStudentBySchool(name);
	}
	
	@GetMapping("/students/result")
	public List<Student> getResult(boolean pass){
		return service.getResultOfStudent(pass);
	}
	
	@GetMapping("/students/{standard}/count")
	public int getStudentByStandard(@PathVariable int standard){
		return service.getStudentCountByStandard(standard);
	}
	
	@GetMapping("/students/strength")
	public int getStrengthBySchool(String school) {
		return service.getSchoolStrength(school);
	}
	
	@GetMapping("/students/toppers")
	public List<Student> getToppers(){
		return service.getTop5Percentage();
	}
	
	@GetMapping("/students/topper/{standard}")
	public List<Student> getTopperByStandard(@PathVariable int standard, String school) {
		return service.getTopperByStandard(school, standard);
		
	}
	
	@PostMapping("/student/add")
	public Student addStudent(@RequestBody Student student) {
		return service.addStudent(student);
	}
	
	@PostMapping("/teacher/add")
	public Teacher addTeacher(@RequestBody Teacher teacher) {
		return service.addTeacher(teacher);
	}
	
	@GetMapping("/students/class_teacher")
	public Teacher getTeacherByStudent(int regNo) {
		return service.getTeacherBySid(regNo);
	}
}
