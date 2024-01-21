package com.example.APIs_demo_2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	// JPQL Query
	@Query("FROM Student WHERE name = :name OR location = :location") // it always starts with FROM if get request
	List<Student> getStudentsByNameOrLocation(@Param("name") String stud_name, @Param("location") String stud_location);
	
	@Transactional
	@Modifying // whenever you are writing JPQL queries for deleteing updating and deleting,
				// use this annotation over
	@Query("DELETE FROM Student WHERE name = :name")
	Integer deleteStudentByName(@Param("name") String name); // here integer return type returns count of rows affected by this query

}
