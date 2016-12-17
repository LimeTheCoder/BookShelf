package com.limethecoder.controller;


import com.limethecoder.data.domain.*;
import com.limethecoder.data.service.BookService;
import com.limethecoder.data.service.LikeService;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class MainController {

    private final static int PAGE_SIZE = 20;
    private final static int PAGES_ON_VIEW = 5;

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @RequestMapping(method = GET)
    public String home(@RequestParam(name = "page", defaultValue = "1") int pageNumber, Model model) {
        if(pageNumber > 0) {
            Page<Book> page = bookService.findAll(new PageRequest(
                    pageNumber - 1, PAGE_SIZE)
            );

            if(page.getTotalElements() == 0) {
                model.addAttribute("error", "No books in database");
                return "home";
            }

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

        return "home";
    }

    @RequestMapping(value = "/user/{login}", method = GET)
    public String userPage(@PathVariable String login, Model model) {
        User user = userService.findOne(login);
        if(user == null) {
            model.addAttribute("message", "No user with login " + login);
            return "error";
        }
        List<Book> liked = likeService.findLikedBooks(user.getLogin());
        if(liked != null) {
            model.addAttribute("liked", liked);
        }

        List<Book> reviewed = bookService.findReviewedBooks(user);
        if(reviewed != null) {
            model.addAttribute("reviewed", reviewed);
        }

        model.addAttribute("user", user);
        return "user_page";
    }
    @RequestMapping(value = "/book/{id}", method = GET)
    public String bookPage(@PathVariable String id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute(book);
        model.addAttribute("newReview", new Review());
        return "book_page";
    }

    @RequestMapping(value = "/book/{id}", method = POST)
    public String addReview(@PathVariable String id,
                            @ModelAttribute("newReview") Review review,
                            Model model, Principal principal) {
        Book book = bookService.findOne(id);
        User user = userService.findOne(principal.getName());
        review.setUser(user);
        review.setDate(new Date());

        if(book.getReviews() == null) {
            book.setReviews(new ArrayList<>());
        }

        book.getReviews().add(review);
        bookService.update(book);
        model.addAttribute(book);

        return "book_page";
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

    @RequestMapping(value = "/getIcon/{login}")
    @ResponseBody
    public byte[] getUserIcon(@PathVariable String login) {
        User user = userService.findOne(login);
        if(user == null) {
            return new byte[]{};
        }
        return userService.loadImage(user);
    }
}
