package com.school.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {
	private int rollNo;
	private String name;
	private String school;
	private int standard;
	private double percentage;
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public Student(int rollNo, String name, String school, int standard, double percentage) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.school = school;
		this.standard = standard;
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", school=" + school + ", standard=" + standard
				+ ", percentage=" + percentage + "]";
	}
	
	public static List<Student> generateStudents(int count) {
        List<Student> studentList = new ArrayList<>();
        Random random = new Random();
        String[] schools = {"DPS", "KVM", "SJHS"};
        String[] firstNames = {
            "Alice", "Bob", "Carol", "David", "Eve", "Frank", "Grace", "Hannah",
            "Ian", "Julia", "Kevin", "Laura", "Michael", "Nora", "Oscar", "Patricia",
            "Quentin", "Rachel", "Samuel", "Tina", "Uma", "Victor", "Wanda", "Xavier",
            "Yvonne", "Zachary", "Aaron", "Bella", "Chris", "Diana", "Ethan", "Fiona",
            "George", "Hannah", "Ian", "Julia", "Kevin", "Laura", "Michael", "Nora",
            "Oscar", "Patricia", "Quentin", "Rachel", "Samuel", "Tina", "Uma", "Victor"
        };
        String[] lastNames = {
            "Johnson", "Smith", "Williams", "Brown", "Davis", "Miller", "Wilson",
            "Taylor", "Anderson", "Roberts", "Lewis", "Harris", "Thompson", "Martinez",
            "Garcia", "Clark", "Robinson", "Baker", "Adams", "White", "Jackson", "Martin",
            "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson", "Walker", "Perez",
            "Hall", "Young", "Allen", "Sanchez", "Wright", "King", "Scott", "Torres",
            "Nguyen", "Hill", "Flores", "Green", "Cox", "Edwards", "Stewart", "Morris",
            "Nguyen", "Murphy", "Rivera"
        };
        String[] schoolNames = {"DPS", "KVM", "SJHS"};

        for (int i = 1; i <= count; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String fullName = firstName + " " + lastName;
            String school = schoolNames[random.nextInt(schoolNames.length)];
            int standard = random.nextInt(12) + 1; // Standards 1 to 12
            double percentage = 50.0 + (random.nextDouble() * 50.0); // Percentages from 50.0 to 100.0
            percentage = Math.round(percentage * 10.0) / 10.0; // Rounded to 1 decimal place

            Student student = new Student(i, fullName, school, standard, percentage);
            studentList.add(student);
        }

        return studentList;
    }
	
}
