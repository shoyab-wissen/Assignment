package com.fullintegration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullintegration.model.Emp;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Integer>{

}
