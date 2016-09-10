package com.limethecoder.data.repository;


import com.limethecoder.data.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByNameAndSurname(String name, String surname);

    @Query(value = "select * from Author limit ?", nativeQuery = true)
    List<Author> findAuthorsLimit(int cnt);
}
