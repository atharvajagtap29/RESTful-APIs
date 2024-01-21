package com.example.APIs_demo_2.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.APIs_demo_2.Model.Student;
import com.example.APIs_demo_2.Service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

	// Used to read the properties from application.properties file
	@Value("${app.name}")
	private String applicationName;

	@Value("${app.version}")
	private String applicationVersion;

	@Autowired
	private StudentService studentService;

	/*------------------------------------------------------ RESTful Services ------------------------------------------------------------*/

	@GetMapping("/appDetails")
	public ResponseEntity<String> getAppDetails() {
		return new ResponseEntity<String>(applicationName + " - " + applicationVersion, HttpStatus.OK);
	}

	// URI - http://localhost:8080/api/students/getStudents
	// @RequestMapping(value = "/show_students" , method = RequestMethod.GET)
	@GetMapping("/getStudents")
	public ResponseEntity<List<Student>> displayStudents(@RequestParam("pageNum") int pageNumber,
			@RequestParam("pageSize") int pageSize) {
		return new ResponseEntity<List<Student>>(studentService.getStudents(pageNumber, pageSize), HttpStatus.OK);
	}

	// URI - http://localhost:8080/api/students/getStudentById/2
	// use of path variable
	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") int id) {
		return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
	}

	// URI - http://localhost:8080/api/students/saveStudent
	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.addStudent(student), HttpStatus.CREATED);
	}

	@PutMapping("/updateStudent")
	public ResponseEntity<Student> updateEntireStudent(@RequestParam("id") int id, @RequestBody Student student) {
		student.setId(id);
		return new ResponseEntity<Student>(studentService.updateStudent(student), HttpStatus.OK);
	}
	
	@PatchMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") int studId, @RequestBody Map<String, Object> fields) {
		return new ResponseEntity<Student>(studentService.updateOnField(studId, fields), HttpStatus.OK);
	}

	// URI - http://localhost:8080/deleteById?id=89
	// using request param annotation
	@DeleteMapping("/deleteById")
	public ResponseEntity<HttpStatus> deleteById(@RequestParam("id") int id) {
		studentService.deleteStudentById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	// URI - http://localhost:8080/getStudentByName?name=Atharva
	@GetMapping("/getStudentByName")
	public ResponseEntity<List<Student>> getStudentByName(@RequestParam("name") String name) {
		return new ResponseEntity<List<Student>>(studentService.getStudentByName(name), HttpStatus.OK);
	}
	
	// URI - http://localhost:8080/getStudentByName?name=Atharva&location=Pune
	@GetMapping("/getStudentByNameAndLocation")
	public ResponseEntity<List<Student>> getStudentByNameAndLocation(@RequestParam("name") String name,
			@RequestParam("location") String location) {
		return new ResponseEntity<List<Student>>(studentService.getStudentByNameAndLocation(name, location),
				HttpStatus.OK);
	}

	@GetMapping("/getStudentByEmailDomain/{domain}")
	public ResponseEntity<List<Student>> getStudentByEmailDomain(@PathVariable("domain") String keyword) {
		return new ResponseEntity<List<Student>>(studentService.getStudentByEmailDomain(keyword), HttpStatus.OK);
	}
	
	@GetMapping("/getStudentByNameOrLocation/{name}/{location}")
	public ResponseEntity<List<Student>> getStudentByNameOrLocation(@PathVariable("name") String stud_name , @PathVariable("location") String stud_location) {
		return new ResponseEntity<List<Student>>(studentService.getStudentByNameOrLocation(stud_name, stud_location), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStudentByName")
	public ResponseEntity<String> deleteStudentByName(@RequestParam("student") String name) {
		return new ResponseEntity<String>(studentService.deleteStudentByName(name)+" no. of rows affected", HttpStatus.OK);
	}

}
