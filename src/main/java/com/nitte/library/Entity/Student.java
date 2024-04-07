package com.nitte.library.Entity;

import lombok.Data;


import jakarta.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String branch;
    private String section;
    private String profilePicturePath;
    
}
