package com.limethecoder.data.service;

import java.util.List;

public interface Service<T, ID> {
    T findOne(ID id);

    T add(T entity);

    void delete(ID id);

    T update(T entity);

    Long count();

    List<T> findAll();
}
