package com.limethecoder.data.repository;


import com.limethecoder.data.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);

    @Query(value = "select * from Book limit ?", nativeQuery = true)
    List<Book> findBooksLimit(int cnt);
}
