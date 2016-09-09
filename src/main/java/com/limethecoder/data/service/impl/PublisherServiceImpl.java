package com.limethecoder.data.service.impl;


import com.limethecoder.data.domain.Publisher;
import com.limethecoder.data.repository.PublisherRepository;
import com.limethecoder.data.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class PublisherServiceImpl extends AbstractService<Publisher> implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findByName(String name) {
        return publisherRepository.findByName(name);
    }

    @Override
    protected JpaRepository<Publisher, Integer> getRepository() {
        return publisherRepository;
    }
}
