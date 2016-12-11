package com.limethecoder.controller;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/books")
public class BookController {

    private static final Logger logger = LoggerFactory
            .getLogger(BookController.class);

    private final static int PAGE_SIZE = 30;
    private final static int PAGES_ON_VIEW = 5;

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String bookList(@RequestParam(name = "page", defaultValue = "1")
                                   int pageNumber, Model model) {
        if(pageNumber > 0) {
            PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
            Page<Book> page = bookService.findAll(pageRequest);

            int begin = Math.max(1, pageNumber - PAGES_ON_VIEW / 2);
            int end = Math.min(begin + PAGES_ON_VIEW - 1, page.getTotalPages());

            if(pageNumber > end) {
                model.addAttribute("message", "Page number out of range");
                return "error";
            }

            if(end - pageNumber < PAGES_ON_VIEW / 2) {
                begin = Math.max(1, end - PAGES_ON_VIEW + 1);
            }

            model.addAttribute("current", pageNumber);
            model.addAttribute("begin", begin);
            model.addAttribute("end", end);
            model.addAttribute("books", page);

        } else {
            model.addAttribute("message", "Page number can't be less that 1");
            return "error";
        }

        return "books";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String bookDetail(@PathVariable String id, Model model) {
        model.addAttribute("message", id);
        return "home";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("message", "new");
        return "home";
    }
}
