package com.nitte.library.Services;

import org.springframework.http.ResponseEntity;

import com.nitte.library.Dto.BorrowedBookDTO;

public interface BorrowBookService {

    ResponseEntity<?> borrowBook(BorrowedBookDTO StudentDto);

    public ResponseEntity<?> getBorrowedBooksByEmail(String email);
    
}
