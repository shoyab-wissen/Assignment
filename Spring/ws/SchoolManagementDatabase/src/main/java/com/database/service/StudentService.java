package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.entity.School;
import com.database.entity.Student;
import com.database.entity.Teacher;
import com.database.repo.StudentRepo;
import com.database.repo.TeacherRepo;

@Service
public class StudentService {
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	TeacherRepo teacherRepo;
	public List<Student> getAllStudents(){
		return studentRepo.findAll();
	}
	
	public Student getStudentByRollNo(int roll, String school) {
		return studentRepo.findOneByRollNoAndSchool(roll, school);
	}
	
	public List<Student> getResultOfStudent(boolean result){
		if(result) 
			return studentRepo.findByPercentageGreaterThanEqual(40);
		return studentRepo.findByPercentageLessThan(40);
	}
	
	public int getStudentCountByStandard(int std) {
		return studentRepo.findByStandard(std).size();
	}
	
	public int getSchoolStrength(String school) {
		return studentRepo.findBySchool(school).size();
	}
	
	public List<Student> getStudentBySchool(String school) {
		return studentRepo.findBySchool(school);
	}
	
	public List<Student> getTop5Percentage(){
		return studentRepo.findTop5ByOrderByPercentageDesc();
	}
	
	public List<Student> getTopperByStandard(String school, int standard){
		return studentRepo.findBySchoolAndStandardOrderByPercentage(school, standard);
	}
	
	public Student addStudent(Student student) {
		studentRepo.save(student);
		return student;
	}
	
	public Teacher getTeacherBySid(int sid) {
		List<Student> student = studentRepo.findBySid(sid);
		if(student.isEmpty()) {
			return null;
		}
		return student.get(0).getTeacher();
//		Teacher teacher = teacherRepo.findByStandard(student.get(0).getStandard()).get(0);
//		return teacher;
	}

	public Teacher addTeacher(Teacher teacher) {
		teacherRepo.save(teacher);
		return teacher;
	}
	
}
