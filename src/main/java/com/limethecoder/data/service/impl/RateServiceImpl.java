package com.limethecoder.data.service.impl;

import com.limethecoder.data.domain.Book;
import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.BookRepository;
import com.limethecoder.data.repository.RateRepository;
import com.limethecoder.data.repository.UserRepository;
import com.limethecoder.data.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RateServiceImpl extends AbstractMongoService<Rate, String>
        implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

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
        if(!userRepository.exists(userId) || !bookRepository.exists(bookId)) {
            return;
        }

        Rate rate = rateRepository.findOne(userId, bookId);
        bookRepository.updateBookRate(bookId, -rate.getValue(), -1);

        rateRepository.delete(rate);
    }

    @Override
    public Rate rate(String userId, String bookId, int value) {
        return add(new Rate(userId, bookId, value));
    }

    @Override
    public Rate add(Rate entity) {
        if(!userRepository.exists(entity.getUserId()) ||
                !bookRepository.exists(entity.getBookId()) ||
                isRated(entity.getUserId(), entity.getBookId())) {
            return null;
        }

        bookRepository.updateBookRate(entity.getBookId(), entity.getValue(), 1);

        return rateRepository.save(entity);
    }

    @Override
    public Rate update(Rate entity) {
        Rate rate = rateRepository.findOne(entity.getUserId(),
                entity.getBookId());
        int diff = entity.getValue() - rate.getValue();
        bookRepository.updateBookRate(entity.getBookId(), diff, 0);
        return rateRepository.save(entity);
    }

    public void delete(String id) {
        Rate rate = rateRepository.findOne(id);

        if(rate == null) {
            return;
        }

        bookRepository.updateBookRate(rate.getBookId(),
                -rate.getValue(), -1);
        rateRepository.delete(rate);
    }

    @Override
    protected MongoRepository<Rate, String> getRepository() {
        return rateRepository;
    }
}
