package com.limethecoder.data.repository.impl;

import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.interfaces.CustomOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public abstract class CustomOperationsRepository<T> implements CustomOperations<T> {
    private final Class<T> tClass = getDomainClass();

    public abstract Class<T> getDomainClass();

    @Autowired
    private MongoOperations mongo;

    @Override
    public T findOne(String userId, String bookId) {
        return mongo.findOne(new Query(where("userId")
                .is(userId).and("bookId")
                .is(bookId)), tClass);
    }

    @Override
    public boolean exists(String userId, String bookId) {
        return findOne(userId, bookId) != null;
    }

    @Override
    public void delete(String userId, String bookId) {
        mongo.remove(new Query(where("userId")
                .is(userId).and("bookId")
                .is(bookId)), tClass);
    }
}
