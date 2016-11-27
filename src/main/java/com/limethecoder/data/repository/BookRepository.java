package com.limethecoder.data.repository;

import com.limethecoder.data.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {
}
