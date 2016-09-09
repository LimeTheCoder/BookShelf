package com.limethecoder.data.service;

import com.limethecoder.data.domain.Publisher;

import java.util.List;

public interface PublisherService extends Service<Publisher> {
    List<Publisher> findByName(String name);
}
