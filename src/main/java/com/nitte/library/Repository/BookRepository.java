package com.nitte.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitte.library.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
   
}

