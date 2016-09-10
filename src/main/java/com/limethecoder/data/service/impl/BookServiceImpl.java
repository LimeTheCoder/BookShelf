package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.repository.BookRepository;
import com.limethecoder.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class BookServiceImpl extends AbstractService<Book> implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findBooksLimit(int cnt) {
        return bookRepository.findBooksLimit(cnt);
    }

    @Override
    protected JpaRepository<Book, Integer> getRepository() {
        return bookRepository;
    }
}
