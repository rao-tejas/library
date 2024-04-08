package com.nitte.library.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nitte.library.Entity.BorrowedBook;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowedBook,Long>{

    @Query("SELECT bb FROM BorrowedBook bb WHERE bb.email = :email AND bb.activeFlag = 'B'")
    List<BorrowedBook> findByEmailAndActiveFlag(String email);
    
    @Query("SELECT bb FROM BorrowedBook bb WHERE bb.email = :email AND bb.bookId = :bookId AND bb.activeFlag = :activeFlag")
    List<BorrowedBook> findByEmailAndBookIdAndActiveFlag(String email, Long bookId, Character activeFlag);   
}
