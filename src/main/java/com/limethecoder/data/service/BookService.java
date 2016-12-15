package com.limethecoder.data.service;


import com.limethecoder.data.domain.Book;

public interface BookService extends Service<Book, String> {
    byte[] loadCover(Book book);
}
