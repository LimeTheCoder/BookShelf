package com.limethecoder.controller;

import com.limethecoder.data.domain.Author;
import com.limethecoder.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorConroller {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(method=RequestMethod.GET)
    public List<Author> authors(
            @RequestParam(value="cnt", defaultValue="10") int cnt) {
        return authorService.findAuthorsLimit(cnt);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String authorProfile(@PathVariable int id, Model model) {
        Author author = authorService.findOne(id);
        model.addAttribute("author", author);
        return "author";
    }
}
