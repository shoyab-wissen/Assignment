package com.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.database.entity.School;
import com.database.entity.Student;
import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	Student findOneByRollNoAndSchool(int rollNo, String school);
	List<Student> findBySchool(String school);
	List<Student> findByPercentageGreaterThanEqual(int above);
	List<Student> findByPercentageLessThan(int below);
	List<Student> findByStandard(int standard);
	@Query("SELECT s FROM Student s ORDER BY s.percentage DESC")
	List<Student> findTop5ByOrderByPercentageDesc();
	List<Student> findBySchoolAndStandardOrderByPercentage(String school, int standard);
	List<Student> findBySid(int sid);
	
}
