package com.limethecoder.controller.editor;


import com.limethecoder.data.domain.Author;
import com.limethecoder.data.service.AuthorService;
import com.limethecoder.data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorEditor extends AbstractEditor<Author> {
    @Autowired
    AuthorService authorService;

    @Override
    protected Service<Author> getService() {
        return authorService;
    }
}
