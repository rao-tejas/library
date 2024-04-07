package com.nitte.library.Dto;

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
