package com.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.database.entities.Employee;
import java.util.List;

@Repository
@Component
public interface EmpRepo extends JpaRepository<Employee, Integer> {
	List<Employee> findByName(String name);
}
