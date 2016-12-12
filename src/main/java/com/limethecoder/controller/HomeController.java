package com.limethecoder.controller;


import com.limethecoder.data.domain.*;
import com.limethecoder.data.service.BookService;
import com.limethecoder.data.service.LikeService;
import com.limethecoder.data.service.RateService;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if(bookService.count() == 0) {
            User user = userService.findOne("logan");
            Review review = new Review();
            review.setDate(new Date(2016, 11, 15));
            review.setUser(user);
            review.setType("positive");
            review.setText("Very interesting book.");
            User user2 = userService.findOne("laymont");
            Review review2 = new Review();
            review2.setDate(new Date(2015, 12, 5));
            review2.setUser(user2);
            review2.setType("negative");
            review2.setText("Stupid story.");

            Book book = new Book();
            book.setPagesCnt(321);
            book.setPublishYear(1996);
            book.setTitle("Harry Potter");
            book.setDescription("Book about magic.");
            book.setCoverUrl("url");
            book.setRateCnt(35);
            book.setReviews(Arrays.asList(review, review2));
            book.setRateValue(121);

            Author author = new Author();
            author.setSurname("Rowling");
            author.setName("Joan");
            author.setBirthDate(new Date(1997, 5, 16));

            Author author2 = new Author();
            author2.setSurname("Jacobson");
            author2.setName("Paul");

            author2.setBirthDate(new Date(1936, 7, 12));
            book.setAuthors(Arrays.asList(author, author2));
            Address address = new Address();
            address.setBuilding("5A");
            address.setStreet("Sunny str.");
            address.setCountry("USA");
            address.setCity("New York");
            address.setZip("56489");
            Publisher publisher = new Publisher();
            publisher.setName("Ababagalamaga");
            publisher.setAddress(address);

            book.setPublisher(publisher);
            book.setGenres(Arrays.asList("detective", "science-fiction"));
            bookService.add(book);
        } else {
            Book book = bookService.findAll().get(0);
            System.out.println(book.getReviews().get(0).getUser().getLogin());
        }


        if(rateService.count() == 0) {
            List<Book> books = bookService.findAll();
            List<User> users = userService.findAll();
            rateService.rate(users.get(2).getLogin(), books.get(0).getId(), 3);
            rateService.rate(users.get(5).getLogin(), books.get(0).getId(), 1);
            rateService.rate(users.get(3).getLogin(), books.get(0).getId(), 5);
            rateService.rate(users.get(4).getLogin(), books.get(0).getId(), 4);

            likeService.like(users.get(3).getLogin(), books.get(0).getId());
            likeService.like(users.get(6).getLogin(), books.get(0).getId());
        } else {
            Book book = bookService.findAll().get(0);
            User user = userService.findAll().get(2);

            Rate rate = rateService.findRate(user.getLogin(), book.getId());
            System.out.println(rate.getUserId() + rate.getValue());
        }

        return "home";
    }
}
