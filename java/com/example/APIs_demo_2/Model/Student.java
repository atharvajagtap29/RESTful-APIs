package com.example.APIs_demo_2.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
public class Student {
	
	// Use these same field names inside the request body in your URL or use @JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@JsonProperty("full_name")
	@Column(name = "student_name", nullable = false)
	@NotBlank(message = "Name must not be null")
	private String name;
	
	//@JsonIgnore
	@Column(name = "student_age", nullable = false)
	//@NotBlank(message = "Age must not be null")  -> We cannot give @NotBlank or @NotEmpty over an integer value
	@NotNull(message = "Age must not be null")
	private int age;
	
	@Column(name = "student_location", nullable = false)
	@NotBlank(message = "Location must not be null")
	private String location;
	
	@Column(name = "student_email", nullable = false)
	@NotBlank(message = "Email must not be null")
	@Email(message = "Please enter the valid email address")
	private String email; 
	
	@Column(name = "student_major", nullable = false)
	@NotBlank(message = "Major must not be null")
	private String major;

// ---------------------------------------------------CONSTANTS------------------------------------------------------------
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt;
}
