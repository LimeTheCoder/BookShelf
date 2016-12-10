package com.limethecoder.data.repository.impl;


import com.limethecoder.data.domain.Book;
import com.limethecoder.data.repository.interfaces.BookOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


public class BookRepositoryImpl implements BookOperations {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateBookRate(String bookId, int valueInc, int cntInc) {
        mongoTemplate.updateFirst(Query.query(where("_id").is(bookId)),
                new Update()
                        .inc("rateValue", valueInc)
                        .inc("rateCnt", cntInc),
                Book.class);
    }
}
