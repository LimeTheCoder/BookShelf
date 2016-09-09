package com.limethecoder.data.service;

import com.limethecoder.data.domain.Author;

import java.util.List;

public interface AuthorService extends Service<Author> {
    List<Author> findByNameAndSurname(String name, String surname);
}
