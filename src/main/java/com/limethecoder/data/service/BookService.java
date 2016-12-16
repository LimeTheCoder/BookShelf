package com.limethecoder.data.service;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.User;

import java.util.List;

public interface BookService extends Service<Book, String> {
    byte[] loadCover(Book book);
    List<Book> findReviewedBooks(User user);
}
