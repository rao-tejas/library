package com.nitte.library.Services;

import org.springframework.http.ResponseEntity;

import com.nitte.library.Dto.StudentDTO;

public interface StudentService {

    ResponseEntity<?> saveStudent(StudentDTO StudentDto);

    ResponseEntity<?> getALLStudent();

	ResponseEntity<?> loginStudent(String email, String password);
    
    ResponseEntity<?> getStudentByEmail(String email);

}
