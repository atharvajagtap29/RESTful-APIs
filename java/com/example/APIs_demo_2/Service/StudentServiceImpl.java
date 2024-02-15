package com.example.APIs_demo_2.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.APIs_demo_2.Model.Student;
import com.example.APIs_demo_2.Repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo repo;
	
	// get all the existing students
	@Override
	public List<Student> getAllStudents() {
		return repo.findAll();
	}
	
	@Override
	// we can receive list of students in pages
	public List<Student> getStudents(int pageNumber, int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize, Direction.ASC, "id"); // whichever field you pass here,
																					// that will be ordered
		return repo.findAll(page).getContent();
	}

	@Override
	public Student addStudent(Student s) {
		return repo.save(s);
	}

	@Override
	public Student getStudentById(int id) {
		Optional<Student> student = repo.findById(id);
		if (student.isPresent()) {
			return student.get();
		}
		throw new RuntimeException("Employee not found for id " + id);
	}

	@Override
	public void deleteStudentById(int id) {
		repo.deleteById(id);
	}

	@Override
	public Student updateStudent(Student s) {
		return repo.save(s); // Difference between this and above save method is, this student object will
								// contain an 'id' as its an existing student, while above save will save a new
								// student where no id is passed to student object
	}

	@Override
	public List<Student> getStudentByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public List<Student> getStudentByNameAndLocation(String name, String location) {
		return repo.findByNameAndLocation(name, location);
	}

	@Override
	public List<Student> getStudentByEmailDomain(String keyword) {
		return repo.findByEmailContaining(keyword);
	}

	// For patch request
	@Override
	public Student updateOnField(int studId, Map<String, Object> fields) {
		Optional<Student> student = repo.findById(studId); // This line will find the student from the id parameter
															// passed in URL

		if (student.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Student.class, key); // here we will get that particular field
																				// need to update
				field.setAccessible(true); // give modify or update access
				ReflectionUtils.setField(field, student.get(), value); // set new value on the field -> of student -> to
																		// 'value'
			});
			repo.save(student.get());
			return student.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Student> getStudentByNameOrLocation(String name, String location) {
		return repo.getStudentsByNameOrLocation(name, location);
	}

	@Override
	public Integer deleteStudentByName(String name) {
		return repo.deleteStudentByName(name);
	}

}
