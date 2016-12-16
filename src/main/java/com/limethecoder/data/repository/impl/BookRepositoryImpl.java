package com.limethecoder.data.repository.impl;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.User;
import com.limethecoder.data.repository.interfaces.BookOperations;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


public class BookRepositoryImpl implements BookOperations {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void deleteReviewsByUser(User user) {
        Query query = new Query(where("reviews.user").is(user.getLogin()));
        Update update = new Update().pull("reviews", new BasicDBObject("user", user.getLogin()));
        mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public List<Book> findReviewedBooks(User user) {
        Query query = new Query(where("reviews.user").is(user.getLogin()));
        return mongoTemplate.find(query, Book.class);
    }
}
