package com.nitte.library.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitte.library.Dto.StudentDTO;
import com.nitte.library.Services.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/Student")
public class StudentController {

	@Autowired
	StudentService StudentService;

	@GetMapping("/all")
	public ResponseEntity<?> getALLStudent() {
		return StudentService.getALLStudent();
	}

	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<?> getStudentByEmail(@PathVariable String email) {

		return StudentService.getStudentByEmail(email);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@RequestBody StudentDTO StudentDto) {
		return StudentService.saveStudent(StudentDto);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginStudent(@RequestBody StudentDTO StudentDto) {
		return StudentService.loginStudent(StudentDto.getEmail(), StudentDto.getPassword());

	}

}
