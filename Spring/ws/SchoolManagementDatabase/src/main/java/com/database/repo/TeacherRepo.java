package com.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.entity.Teacher;
import java.util.List;


public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
	List<Teacher> findByStandard(int standard);
}
