package com.limethecoder.util.generator;


import com.github.javafaker.Faker;
import com.limethecoder.data.domain.*;
import com.limethecoder.data.service.BookService;
import com.limethecoder.data.service.ConstantsService;
import com.limethecoder.data.service.LikeService;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Generator {
    private Faker faker;
    private UserService userService;
    private BookService bookService;
    private LikeService likeService;
    private ConstantsService constantsService;
    private List<String> logins;

    @Autowired
    public Generator(UserService userService,
                     BookService bookService,
                     LikeService likeService,
                     ConstantsService constantsService) {
        faker = new Faker();
        this.userService = userService;
        this.bookService = bookService;
        this.likeService = likeService;
        this.constantsService = constantsService;
        logins = userService.getAllLogins();
    }

    public User generateUser() {
        User user = new User();
        user.setLogin(faker.name().username());
        user.setName(faker.name().firstName());
        user.setSurname(faker.name().lastName());
        user.setCity(faker.address().cityName());
        user.setPassword(faker.name().username());
        return user;
    }

    public Address generateAddress() {
        Address address = new Address();
        address.setCity(faker.address().city());
        address.setCountry(faker.address().country());
        address.setStreet(faker.address().streetName());
        address.setBuilding(faker.address().buildingNumber());
        address.setZip(faker.address().zipCode());
        return address;
    }

    public Review generateReview(User user) {
        Review review = new Review();
        review.setDate(faker.date().between(new Date(2012, 1, 1),
                new Date(2017, 1, 1)));
        review.setText(faker.lorem().fixedString(35));
        review.setRate(faker.number().randomDigit() % 5 + 1);
        review.setUser(user);
        return review;
    }

    public Publisher generatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setAddress(generateAddress());
        publisher.setName(faker.book().publisher());

        return publisher;
    }

    public Author generateAuthor() {
        Author author = new Author();
        author.setBirthDate(faker.date().between(new Date(1815, 1, 1),
                new Date(1998, 1, 1)));
        author.setName(faker.name().firstName());
        author.setSurname(faker.name().lastName());
        return author;
    }

    public Book createBook() {
        Book book = new Book();
        book.setPublisher(generatePublisher());

        List<Author> authors = new ArrayList<>();
        authors.add(generateAuthor());
        if(faker.number().randomDigit() % 2 == 1) {
            authors.add(generateAuthor());
        }
        book.setAuthors(authors);

        book.setTitle(faker.book().title());
        List<String> genres = new ArrayList<>();
        List<String> constants = constantsService
                .getConstantsByType(ConstantsService.GENRE_TYPES);
        int r = faker.number().randomDigit();
        genres.add(constants.get(r % constants.size()));
        int r2 = faker.number().randomDigit();
        if(r != r2) {
            genres.add(constants.get(r2 % constants.size()));
        }
        book.setGenres(genres);

        book.setPagesCnt(faker.number().numberBetween(70, 1024));
        book.setPublishYear(faker.number().numberBetween(1912, 2017));

        List<Review> reviews = new ArrayList<>();
        int num = faker.number().randomDigit() % 10;
        book.setReviews(reviews);
        for(int i = 0; i < num; i++) {
            book.addReview(generateReview(userService.findOne(
                    logins.get(faker.number().randomDigit() % logins.size()))));
        }

        book.setDescription(faker.lorem().fixedString(35));
        Book added = bookService.add(book);
        int q = faker.number().randomDigit() % 15;
        for(int i = 0; i < q ;i++) {
            likeService.like(logins.get(faker.number().randomDigit() % logins.size()),
                    book.getId());
        }
        return book;
    }
}
