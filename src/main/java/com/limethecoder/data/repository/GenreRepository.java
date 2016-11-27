package com.limethecoder.data.repository;

import com.limethecoder.data.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GenreRepository extends MongoRepository<Genre, String> {
}
