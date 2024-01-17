package com.example.APIs_demo_2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.APIs_demo_2.Model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>, PagingAndSortingRepository<Student, Integer> {
	// Derived query method. Also known as finder or query method
	// It starts by 'findBy' followed by the field name in entity class in camelCase
	
	// For query methods watch the video from 2:59:00
	List<Student> findByName(String name);
	
	// SELECT * FROM students WHERE name='name' AND location='location' 
	List<Student> findByNameAndLocation(String name, String location);
	
	// SELECT * FROM students WHERE email LIKE %gmail% 
	List<Student> findByEmailContaining(String keyword);
}
