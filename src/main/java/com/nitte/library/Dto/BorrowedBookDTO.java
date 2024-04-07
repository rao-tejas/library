package com.nitte.library.Dto;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class BorrowedBookDTO {
   
    private Long id;

    private String email; 
    private Long bookId;
    private LocalDateTime issuedDate;
    private LocalDateTime returnDate;
    private double penalty;
}
