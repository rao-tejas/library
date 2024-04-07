package com.nitte.library.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nitte.library.Dto.StudentDTO;
import com.nitte.library.Entity.Book;
import com.nitte.library.Entity.Student;
import com.nitte.library.Repository.StudentRepository;
import com.nitte.library.Services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository StudentRepo;

    @Override
    public ResponseEntity<?> saveStudent(StudentDTO StudentDto) {
        boolean emailExist = StudentRepo.existsByEmail(StudentDto.getEmail());
        System.out.println("service"+StudentDto.getName());
        if (!emailExist) {
            Student Student = new Student();
            Student.setName(StudentDto.getName());
            Student.setEmail(StudentDto.getEmail());
            Student.setPassword(StudentDto.getPassword());
            Student.setPhoneNumber(StudentDto.getPhoneNumber());
            Student.setBranch(StudentDto.getBranch());
            Student.setSection(StudentDto.getSection());
            Student resStudent=StudentRepo.save(Student);

            return new ResponseEntity<>(resStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Email already exists!", HttpStatus.CONFLICT);

        }

    }

    @Override
    public ResponseEntity<?> getALLStudent() {
       List<Student> StudentList=StudentRepo.findAll();
       if(StudentList!=null){
        return  new ResponseEntity<>(StudentList, HttpStatus.OK);
       }
    return new ResponseEntity<>("Student List is empty", HttpStatus.CONFLICT);
    }

	@Override
public ResponseEntity<String> loginStudent(String email, String password) {
    List<Student> students = StudentRepo.findByEmailAndPassword(email, password);

    if (!students.isEmpty()) {
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Login failed", HttpStatus.CONFLICT);
    }
}
@Override
public ResponseEntity<?> getStudentByEmail(String email) {
    try {
        Student student = StudentRepo.findByEmail(email);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found for email: " + email, HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>("An error occurred while fetching student details", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}


