package com.nitte.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitte.library.Dto.BorrowedBookDTO;
import com.nitte.library.Services.BorrowBookService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/transaction/borrowBook")
public class BorrowedBookController {
    @Autowired
    BorrowBookService borrowBookService;

   
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowedBookDTO book) {
        return borrowBookService.borrowBook(book);
    }


    
    
   
}
