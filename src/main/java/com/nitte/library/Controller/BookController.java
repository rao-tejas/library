package com.nitte.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitte.library.Dto.BookDTO;
import com.nitte.library.Entity.Book;
import com.nitte.library.Services.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/Book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/all")
	public ResponseEntity<?> getALLBooks(){
		return bookService.getALLBooks();
	}

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody BookDTO book) {
        return bookService.saveBook(book);
    }


    
    
   
}
