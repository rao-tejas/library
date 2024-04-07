package com.nitte.library.Entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;
    private String author;
    private String position;
    private String department;
    private int count;
    private Integer originalcount;
    private String coverImage;
}
