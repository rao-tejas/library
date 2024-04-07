package com.nitte.library.Services;

import org.springframework.http.ResponseEntity;

import com.nitte.library.Dto.BookDTO;
import com.nitte.library.Entity.Book;

public interface BookService {

    ResponseEntity<?> getALLBooks();

    ResponseEntity<?> saveBook(BookDTO StudentDto);

    
}
