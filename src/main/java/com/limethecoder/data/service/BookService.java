package com.limethecoder.data.service;


import com.limethecoder.data.domain.Book;

import java.util.List;

public interface BookService extends Service<Book> {
    List<Book> findByTitle(String title);
}
