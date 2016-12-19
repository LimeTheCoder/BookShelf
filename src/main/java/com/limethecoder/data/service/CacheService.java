package com.limethecoder.data.service;

import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.User;

import java.util.List;


public interface CacheService {
    String BOOKS_KEY = "books";
    String SEPARATOR = "&";
    String PAGE = "p=";
    String QUERY = "q=";
    String RANGE = "range";
    int EXPIRE_TIME = 300;

    void addBooks(List<Book> books, int page, String query);
    void addBooks(String key, List<Book> books);
    void addUser(User user);
    void addImage(String key, byte[] image);
    void add(String key, String value);

    List<Book> getBooks(int page, String query);
    List<Book> getBooks(String key);
    User getUser(String login);
    byte[] getImage(String key);
    String get(String key);

    boolean exists(String typeKey, int page, String query);
    boolean exists(String key);

    void invalidateCache();
    void invalidate(String key);

    void onBookUpdate(Book book);
    void onBookDelete(Book book);
    void onBookInsert(Book book);
}
