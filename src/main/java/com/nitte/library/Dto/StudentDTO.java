package com.nitte.library.Dto;


import java.util.List;

import lombok.Data;


@Data
public class StudentDTO {
   
    private String name;
    private String email;
    private String password;
    private String branch;
    private String section;
    private String phoneNumber;
    private String profilePicturePath;

}

