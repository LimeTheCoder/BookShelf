package com.limethecoder.data.service;

import com.limethecoder.data.domain.Constants;


public interface ConstantsService {
    String GENRE_TYPES = "genres";
    String REVIEW_TYPES = "reviewsType";

    Constants getInstance();
    Constants addConstant(String  element, String collection);
    void deleteConstant(String element, String collection);
    boolean isExistsConstant(String element, String collection);
    long getCnt(String collection);
}
