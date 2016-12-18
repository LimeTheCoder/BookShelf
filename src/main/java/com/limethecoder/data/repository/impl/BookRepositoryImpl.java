package com.limethecoder.data.repository.impl;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.User;
import com.limethecoder.data.repository.interfaces.BookOperations;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
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

    @Override
    public Page<Book> fullTextSearch(String text, Pageable pageable) {
        Query query = TextQuery.queryText(new TextCriteria().matchingPhrase(text)).sortByScore();
        long total = mongoTemplate.count(query, Book.class);
        List<Book> content = mongoTemplate.find(query.with(pageable), Book.class);

        return new PageImpl<>(content, pageable, total);
    }
}
