package com.limethecoder.data.service;


import com.limethecoder.data.domain.Rate;


public interface RateService extends Service<Rate, String> {
    Rate findRate(String userId, String bookId);
    boolean isRated(String userId, String bookId);
    void delete(String userId, String bookId);
    Rate rate(String userId, String bookId, int value);
}
