package com.limethecoder.data.service;

import java.util.List;

public interface Service<T> {
    T findOne(int id);

    T add(T entity);

    void delete(int id);

    T update(T entity);

    Long count();

    List<T> findAll();
}
