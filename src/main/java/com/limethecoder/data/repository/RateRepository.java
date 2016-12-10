package com.limethecoder.data.repository;

import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.interfaces.CustomOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface RateRepository extends MongoRepository<Rate, String>, CustomOperations<Rate> {
    List<Rate> findByUserId(String userId);
    List<Rate> findByBookId(String bookId);
}
