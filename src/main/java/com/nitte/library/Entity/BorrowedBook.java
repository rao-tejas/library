package com.nitte.library.Entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Data
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; 
    private Long bookId;
    private LocalDateTime issuedDate;
    private LocalDateTime returnDate;
    private Character activeFlag;
    private double penalty;
}
