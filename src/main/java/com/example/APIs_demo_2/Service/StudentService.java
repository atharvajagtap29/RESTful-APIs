package com.example.APIs_demo_2.Service;

import java.util.List;
import java.util.Map;

import com.example.APIs_demo_2.Model.Student;

public interface StudentService {

	List<Student> getStudents(int pageNumber, int pageSize);
	
	Student addStudent(Student s);
	
	Student getStudentById (int id);
	
	void deleteStudentById (int id);
	
	Student updateStudent(Student s);
	
	List<Student> getStudentByName(String name);
	
	List<Student> getStudentByNameAndLocation(String name, String location);
	
	List<Student> getStudentByEmailDomain(String keyword);
	
	Student updateOnField(int studId, Map<String, Object> fields);
}
