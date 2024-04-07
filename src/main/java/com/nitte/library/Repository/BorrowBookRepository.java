package com.nitte.library.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitte.library.Entity.BorrowedBook;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowedBook,Long>{
   
}
