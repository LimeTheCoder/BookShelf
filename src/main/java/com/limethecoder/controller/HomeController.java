package com.limethecoder.controller;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    BookService service;

    @RequestMapping(method = GET)
    public String home(Model model) {
        model.addAttribute("message", "It's working!!");
        Book book = service.findByTitle("Seven Wolfs").get(0);
        book.getAuthors().forEach(
                (x) -> System.out.println(x.getName() + " " + x.getSurname())
        );
        return "home";
    }

}
