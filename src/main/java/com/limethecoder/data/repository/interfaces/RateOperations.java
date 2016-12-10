package com.limethecoder.data.repository.interfaces;


import com.limethecoder.data.domain.Rate;

public interface RateOperations {
    Rate findOne(String userId, String bookId);
    boolean exists(String userId, String bookId);
    void delete(String userId, String bookId);
}
