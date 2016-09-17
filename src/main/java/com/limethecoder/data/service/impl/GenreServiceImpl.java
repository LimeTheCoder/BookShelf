package com.limethecoder.data.service.impl;

import com.limethecoder.data.domain.Genre;
import com.limethecoder.data.repository.GenreRepository;
import com.limethecoder.data.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends AbstractService<Genre> implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    protected JpaRepository<Genre, Integer> getRepository() {
        return genreRepository;
    }
}
