package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.Author;
import com.limethecoder.data.repository.AuthorRepository;
import com.limethecoder.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class AuthorServiceImpl extends AbstractService<Author> implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name, surname);
    }

    @Override
    protected JpaRepository<Author, Integer> getRepository() {
        return authorRepository;
    }
}
