package com.database.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.database.entity.Student;
import com.database.service.StudentService;

@WebMvcTest
class StudentControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private StudentService service;

    private Student student;
    
    @BeforeEach
    void setUp() {
//        student = new Student(1, "Shoyab", "DPS", 12, 100.0);
    	student = new Student();
    }

    private List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        return students;
    }

    @Test
    void testFetchAllStudent() throws Exception {
        when(service.getAllStudents()).thenReturn(getStudents());
        
        mvc.perform(get("/students"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name").value("Shoyab"))
           .andExpect(jsonPath("$[0].percentage").value(100.0));
    }

    @Test
    void testGetStudent() throws Exception {
        when(service.getStudentByRollNo(1, "DPS")).thenReturn(student);

        mvc.perform(get("/student/{rollNo}", 1).param("school", "DPS"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Shoyab"))
           .andExpect(jsonPath("$.percentage").value(100.0));
    }

    @Test
    void testGetStudentBySchool() throws Exception {
        when(service.getStudentBySchool("DPS")).thenReturn(getStudents());

        mvc.perform(get("/student/school").param("name", "DPS"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].school").value("DPS"));
    }

    @Test
    void testGetResult() throws Exception {
        when(service.getResultOfStudent(true)).thenReturn(getStudents());

        mvc.perform(get("/students/result").param("pass", "true"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name").value("Shoyab"));
    }

    @Test
    void testGetStudentByStandard() throws Exception {
        when(service.getStudentCountByStandard(12)).thenReturn(1);

        mvc.perform(get("/students/{standard}/count", 12))
           .andExpect(status().isOk())
           .andExpect(content().string("1"));
    }

    @Test
    void testGetStrengthBySchool() throws Exception {
        when(service.getSchoolStrength("DPS")).thenReturn(50);

        mvc.perform(get("/students/strength").param("school", "DPS"))
           .andExpect(status().isOk())
           .andExpect(content().string("50"));
    }

    @Test
    void testGetToppers() throws Exception {
        when(service.getTop5Percentage()).thenReturn(getStudents());

        mvc.perform(get("/students/toppers"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name").value("Shoyab"))
           .andExpect(jsonPath("$[0].percentage").value(100.0));
    }

    @Test
    void testGetTopperByStandard() throws Exception {
        when(service.getTopperByStandard("DPS", 12)).thenReturn(getStudents());

        mvc.perform(get("/students/topper/{standard}", 12).param("school", "DPS"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].name").value("Shoyab"));
    }

    @Test
    void testAddStudent() throws Exception {
        when(service.addStudent(student)).thenReturn(student);

        mvc.perform(post("/student/add")
        		.param("rollNo", "1")
                .param("name", "Shoyab")
                .param("school", "DPS")
                .param("standard", "12")
                .param("age", "22")
                .param("percentage", "100.0"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Shoyab"))
           .andExpect(jsonPath("$.percentage").value(100.0));

        verify(service).addStudent(student); // Verify service method was called
    }
}
