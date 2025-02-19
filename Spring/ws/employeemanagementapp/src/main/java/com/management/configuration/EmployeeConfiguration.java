package com.management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.management.employee.*;

@Configuration
public class EmployeeConfiguration {
	@Bean("CEO")
	@Scope("singleton")
	public CEO getCeo() {
		return CEO.getObject();
	}
	
	@Bean("Employee")
	@Scope("prototype")
	public Employee getManager(int type) {
		return Employee.getEmployee(type, "", 0, 0);
	}
//	@Bean("Clerk")
//	@Scope("prototype")
//	public Employee getClerk() {
//		return Employee.getEmployee(2, "", 0, 0);
//	}
//	@Bean("Programmer")
//	@Scope("prototype")
//	public Employee getProgrammer() {
//		return Employee.getEmployee(3, "", 0, 0);
//	}
}
