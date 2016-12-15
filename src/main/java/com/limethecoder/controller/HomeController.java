package com.limethecoder.controller;


import com.limethecoder.data.domain.*;
import com.limethecoder.data.service.BookService;
import com.limethecoder.data.service.LikeService;
import com.limethecoder.data.service.RateService;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private RateService rateService;
    @Autowired
    private LikeService likeService;

    @RequestMapping(method = GET)
    public String home(Model model) {
        model.addAttribute("message", "It's working!!");
        model.addAttribute("book", bookService.findAll().get(1));

        return "home";
    }

    @RequestMapping(value = "/getCover/{id}")
    @ResponseBody
    public byte[] getBookCover(@PathVariable String id) {
        Book book = bookService.findOne(id);
        if(book == null) {
            return new byte[]{};
        }
        return bookService.loadCover(book);
    }
}
