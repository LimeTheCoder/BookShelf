package com.limethecoder.controller;

import com.limethecoder.data.domain.Book;
import com.limethecoder.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String authorProfile(@PathVariable int id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Book> books(
            @RequestParam(value="cnt", defaultValue="10") int cnt) {
        return bookService.findBooksLimit(cnt);
    }
}
