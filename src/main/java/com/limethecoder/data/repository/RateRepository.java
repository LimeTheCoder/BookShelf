package com.limethecoder.data.repository;

import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.interfaces.CustomOperations;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RateRepository extends MongoRepository<Rate, String>, CustomOperations<Rate> {
}
