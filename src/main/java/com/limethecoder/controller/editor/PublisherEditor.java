package com.limethecoder.controller.editor;


import com.limethecoder.data.domain.Publisher;
import com.limethecoder.data.service.PublisherService;
import com.limethecoder.data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherEditor extends AbstractEditor<Publisher> {
    @Autowired
    PublisherService publisherService;

    @Override
    protected Service<Publisher> getService() {
        return publisherService;
    }
}
