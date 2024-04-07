package com.nitte.library.ServicesImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nitte.library.Dto.BookDTO;
import com.nitte.library.Entity.Book;
import com.nitte.library.Repository.BookRepository;
import com.nitte.library.Services.BookService;

@Service
public class BookServiceImpl implements BookService {
   @Autowired
   BookRepository bookRepo;

   @Override
   public ResponseEntity<?> getALLBooks() {
      List<Book> patientList = bookRepo.findAll();
      if (patientList != null) {
         return new ResponseEntity<>(patientList, HttpStatus.OK);
      }
      return new ResponseEntity<>("Book List is empty", HttpStatus.CONFLICT);
   }

   @Override
   public ResponseEntity<?> saveBook(BookDTO book) {
      try {
         Book bookEntity = new Book();
         bookEntity.setBookName(book.getBookName());
         bookEntity.setAuthor(book.getAuthor());
         bookEntity.setCount(book.getCount());
         bookEntity.setPosition(book.getPosition());
         bookEntity.setDepartment(book.getDepartment());
         bookEntity.setCoverImage(book.getCoverImage());
         bookEntity.setOriginalcount(book.getOriginalcount());;
         Book savedBook = bookRepo.save(bookEntity);
         return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
      } catch (Exception e) {
         // Handle exceptions such as database errors
         return new ResponseEntity<>("Failed to save book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

}
