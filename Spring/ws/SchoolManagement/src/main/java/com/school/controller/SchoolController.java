package com.school.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.Student;
import org.springframework.web.bind.annotation.RequestParam;


@RestController()
public class SchoolController {
	private static List<Student> students = Student.generateStudents(50);
	
	@GetMapping("/students")
	public List<Student> fetchAllStudent(){
		return students;
	}
	
	@GetMapping("/student/{rollNo}")
	public Student getStudent(@PathVariable int rollNo) {
	 	List<Student> student = students.stream().filter((Student stu)->stu.getRollNo() == rollNo).collect(Collectors.toList());
		if(student.isEmpty()) {
			return null;
		}
		return student.get(0);
	}
	
	@GetMapping("/student/school")
	public List<Student> getStudentBySchool(String name){
		List<Student> studentBySchool = students.stream().filter((Student student) -> student.getSchool() == name).collect(Collectors.toList());
		return studentBySchool;
	}
	
	@GetMapping("/students/result")
	public List<Student> getResult(boolean pass){
		return students.stream().filter((Student student) -> student.getPercentage() >= 40.0 == pass).collect(Collectors.toList());
	}
	
	@GetMapping("/students/{standard}/count")
	public List<Student> getStudentByStandard(@PathVariable int standard){
		return students.stream().filter((Student student) -> student.getStandard()== standard).collect(Collectors.toList());
	}
	
	@GetMapping("/students/strength")
	public int getStrengthBySchool(String school) {
		return students.stream().filter((Student student) -> student.getSchool()== school).collect(Collectors.toList()).size();
	}
	
	@GetMapping("/students/toppers")
	public List<Student> getToppers(){
		int top5Perc = (int) Math.ceil(students.size() * 0.05);
		return students.stream().sorted((Student student1, Student student2) -> (int) (student1.getPercentage() - student2.getPercentage())).limit(top5Perc).collect(Collectors.toList());
	}
	
	@GetMapping("/students/topper/{standard}")
	public Student getTopperByStandard() {
		return students.stream().max((Student student1, Student student2) -> (int) (student1.getPercentage() - student2.getPercentage())).get();
	}
}
