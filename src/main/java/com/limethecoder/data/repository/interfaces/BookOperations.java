package com.limethecoder.data.repository.interfaces;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.User;

import java.util.List;

public interface BookOperations {
    void deleteReviewsByUser(User user);
    List<Book> findReviewedBooks(User user);
}
