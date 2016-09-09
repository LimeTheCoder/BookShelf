package com.limethecoder.data.service.impl;

import com.limethecoder.data.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<T> implements Service<T> {

    @Override
    public T findOne(int id) {
        return getRepository().findOne(id);
    }

    @Override
    public Long count() {
        return getRepository().count();
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T add(T entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    public T update(T entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    public void delete(int id) {
        getRepository().delete(id);
    }

    protected abstract JpaRepository<T, Integer> getRepository();
}

