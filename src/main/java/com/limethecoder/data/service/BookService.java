package com.limethecoder.data.service;


import com.limethecoder.data.domain.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookService extends Service<Book> {
    List<Book> findByTitle(String title);

    @Query(value = "select * from Book limit ?", nativeQuery = true)
    List<Book> findBooksLimit(int cnt);
}
