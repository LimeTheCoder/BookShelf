package com.limethecoder.data.service.impl;

import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.RateRepository;
import com.limethecoder.data.repository.interfaces.RateOperations;
import com.limethecoder.data.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;


@Service
public class RateServiceImpl extends AbstractMongoService<Rate, String>
        implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Rate findRate(String userId, String bookId) {
        return rateRepository.findOne(userId, bookId);
    }

    @Override
    public boolean isRated(String userId, String bookId) {
        return rateRepository.exists(userId, bookId);
    }

    @Override
    public void delete(String userId, String bookId) {
        rateRepository.delete(userId, bookId);
    }

    @Override
    public Rate rate(String userId, String bookId, int value) {
        return rateRepository.insert(new Rate(userId, bookId, value));
    }

    @Override
    protected MongoRepository<Rate, String> getRepository() {
        return rateRepository;
    }
}
