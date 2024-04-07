package com.nitte.library.Dto;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BookDTO {
   
  
    private String bookName;
    private String author;
    private int count;
    private Integer originalcount;
    private String position;
    private String department;
    private String coverImage;
}
