package com.limethecoder.data.repository;


import com.limethecoder.data.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByNameAndSurname(String name, String surname);
}
