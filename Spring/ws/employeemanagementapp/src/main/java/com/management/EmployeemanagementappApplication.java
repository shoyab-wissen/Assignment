package com.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.management.employee.EmployeeManagement;

@SpringBootApplication
public class EmployeemanagementappApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EmployeemanagementappApplication.class, args);
		EmployeeManagement man = (EmployeeManagement) ctx.getBean("empMan", ctx);
		man.main(args);
	}
}
