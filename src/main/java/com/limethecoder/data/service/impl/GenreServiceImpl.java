package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.Genre;
import com.limethecoder.data.repository.GenreRepository;
import com.limethecoder.data.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends AbstractMongoService<Genre, String>
        implements GenreService {

    private GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    protected MongoRepository<Genre, String> getRepository() {
        return repository;
    }
}
