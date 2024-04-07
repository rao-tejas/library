package com.nitte.library.ServicesImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nitte.library.Dto.BookDTO;
import com.nitte.library.Dto.BorrowedBookDTO;
import com.nitte.library.Entity.Book;
import com.nitte.library.Entity.BorrowedBook;
import com.nitte.library.Entity.Student;
import com.nitte.library.Repository.BookRepository;
import com.nitte.library.Repository.BorrowBookRepository;
import com.nitte.library.Repository.StudentRepository;
import com.nitte.library.Services.BookService;
import com.nitte.library.Services.BorrowBookService;
import java.time.LocalDateTime;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    @Autowired
    BorrowBookRepository borrowedBookRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public ResponseEntity<?> borrowBook(BorrowedBookDTO borrowedBookDTO) {
        try {
            // Check if the book with the given ID exists
            Optional<Book> optionalBook = bookRepository.findById(borrowedBookDTO.getBookId());
            System.out.println(optionalBook);
            if (optionalBook.isPresent()) {
                // Check if the email exists in the student repository
                Optional<Student> optionalStudent = Optional.of(studentRepository.findByEmail(borrowedBookDTO.getEmail()));
                if (optionalStudent.isPresent()) {
                    Book book = optionalBook.get();
                    int count = book.getCount();
                    if (count > 0) {
                        // Reduce the count of the book by 1
                        book.setCount(count - 1);
                        bookRepository.save(book);
    
                        // Convert BorrowedBookDTO to BorrowedBook entity
                        BorrowedBook borrowedBook = new BorrowedBook();
                        borrowedBook.setEmail(borrowedBookDTO.getEmail());
                        borrowedBook.setBookId(borrowedBookDTO.getBookId());
    
                        LocalDateTime issuedDateTime = LocalDateTime.now();
                        borrowedBook.setIssuedDate(issuedDateTime);
    
                        // Set return date after 30 days from issued date
                        LocalDateTime returnDateTime = issuedDateTime.plusDays(30);
                        borrowedBook.setReturnDate(returnDateTime);
    
                        // Save the BorrowedBook entity
                        borrowedBookRepository.save(borrowedBook);
    
                        // Return success response with status "success"
                        return ResponseEntity.ok().body("Borrowed book successfully. Status: success");
                    } else {
                        // Book is out of stock
                        return ResponseEntity.badRequest().body("Book is out of stock.");
                    }
                } else {
                    // Email not found in the student repository
                    return ResponseEntity.badRequest().body("Email not found.");
                }
            } else {
                // Book with the given ID not found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while borrowing the book.");
        }
    }
    
}
