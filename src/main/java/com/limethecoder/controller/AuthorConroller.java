package com.limethecoder.controller;

import com.limethecoder.data.domain.Author;
import com.limethecoder.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute(new Author());
        return "addAuthor";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submit(@ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors())
            return "error";

        Author saved = authorService.add(author);

        return "redirect:/authors/" + saved.getId();
    }
}
