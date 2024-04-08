package com.nitte.library.Services;

import org.springframework.http.ResponseEntity;

import com.nitte.library.Dto.BorrowedBookDTO;

public interface BorrowBookService {

    ResponseEntity<?> borrowBook(BorrowedBookDTO BookDTO);

    ResponseEntity<?> returnBook(BorrowedBookDTO bookDTO);


    public ResponseEntity<?> getBorrowedBooksByEmail(String email);
    
}
