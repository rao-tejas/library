package com.nitte.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitte.library.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    boolean existsByEmail(String email);

	List<Student> findByEmailAndPassword(String email, String password);

    Student findByEmail(String email);
}
