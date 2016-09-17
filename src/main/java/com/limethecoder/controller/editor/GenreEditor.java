package com.limethecoder.controller.editor;


import com.limethecoder.data.domain.Genre;
import com.limethecoder.data.service.GenreService;
import com.limethecoder.data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreEditor extends AbstractEditor<Genre> {
    @Autowired
    GenreService genreService;

    @Override
    protected Service<Genre> getService() {
        return genreService;
    }
}
