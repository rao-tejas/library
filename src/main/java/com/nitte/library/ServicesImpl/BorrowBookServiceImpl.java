package com.nitte.library.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    BookService bookService;

    @Override
    public ResponseEntity<?> borrowBook(BorrowedBookDTO borrowedBookDTO) {
        try {
            // Check if the book exists
            Optional<Book> optionalBook = bookRepository.findById(borrowedBookDTO.getBookId());
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                int count = book.getCount();
                if (count > 0) {
                    // Check if the email exists in the student entity
                    Student student = studentRepository.findByEmail(borrowedBookDTO.getEmail());
                    if (student == null) {
                        return ResponseEntity.badRequest()
                                .body("{\"status\": \"failed\", \"message\": \"Student with provided email not found.\"}");
                    }

                    // Check if the book is already borrowed by the student with an active flag 'B'
                    List<BorrowedBook> existingBorrowedBook = borrowedBookRepository
                            .findByEmailAndBookIdAndActiveFlag(borrowedBookDTO.getEmail(),
                                    borrowedBookDTO.getBookId(), 'B');
                    if (!existingBorrowedBook.isEmpty()) {
                        return ResponseEntity.ok()
                                .body("{\"status\": \"failed\", \"message\": \"You already have this book.\"}");
                    }

                    // Proceed with borrowing the book
                    book.setCount(count - 1);
                    bookRepository.save(book);

                    BorrowedBook borrowedBook = new BorrowedBook();
                    borrowedBook.setEmail(borrowedBookDTO.getEmail());
                    borrowedBook.setBookId(borrowedBookDTO.getBookId());
                    borrowedBook.setActiveFlag('B');

                    LocalDateTime issuedDateTime = LocalDateTime.now();
                    borrowedBook.setIssuedDate(issuedDateTime);

                    LocalDateTime returnDateTime = issuedDateTime.plusDays(30);
                    borrowedBook.setReturnDate(returnDateTime);

                    borrowedBookRepository.save(borrowedBook);

                    return ResponseEntity.ok().body("{\"status\": \"success\"}");
                } else {
                    return ResponseEntity.ok()
                            .body("{\"status\": \"failed\", \"message\": \"Book is out of stock.\"}");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"failed\", \"message\": \"An error occurred while borrowing the book.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getBorrowedBooksByEmail(String email) {
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByEmailAndActiveFlag(email);

        List<BorrowedBookDTO> borrowedBookDTOs = borrowedBooks.stream()
                .map(borrowedBook -> {
                    BorrowedBookDTO borrowedBookDTO = new BorrowedBookDTO();
                    borrowedBookDTO.setId(borrowedBook.getId());
                    borrowedBookDTO.setEmail(borrowedBook.getEmail());
                    borrowedBookDTO.setBookId(borrowedBook.getBookId());
                    borrowedBookDTO.setIssuedDate(borrowedBook.getIssuedDate());
                    borrowedBookDTO.setReturnDate(borrowedBook.getReturnDate());
                    borrowedBookDTO.setPenalty(borrowedBook.getPenalty());
                    borrowedBookDTO.setActiveFlag(borrowedBook.getActiveFlag());


                    BookDTO bookDTO = bookService.getBookDetails(borrowedBook.getBookId());
                    borrowedBookDTO.setBook(bookDTO);

                    return borrowedBookDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(borrowedBookDTOs);
    }

    @Override
    public ResponseEntity<?> returnBook(BorrowedBookDTO bookDTO) {
        if (bookDTO.getEmail() == null || bookDTO.getBookId() == null) {
            return ResponseEntity.ok()
                    .body("{\"status\": \"failed\", \"message\": \"Email or bookId not provided.\"}");
        }

        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByEmailAndBookIdAndActiveFlag(bookDTO.getEmail(),
                bookDTO.getBookId(), 'B');

        if (!borrowedBooks.isEmpty()) {
            BorrowedBook borrowedBook = borrowedBooks.get(0);
            borrowedBook.setActiveFlag('R');
            borrowedBookRepository.save(borrowedBook);

            return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Book returned successfully.\"}");
        } else {
            return ResponseEntity.ok()
                    .body("{\"status\": \"failed\", \"message\": \"No borrowed book found for the given email and bookId.\"}");
        }
    }

}
