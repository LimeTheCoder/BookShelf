package com.limethecoder.config;

import com.limethecoder.data.repository.UserRepository;
import com.limethecoder.util.converter.BookReadConverter;
import com.limethecoder.util.converter.BookWriteConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories("com.limethecoder.data.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected String getDatabaseName() {
        return "bookshelf";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost", 27100);
    }


    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new BookWriteConverter());
        converters.add(new BookReadConverter(userRepository));
        return new CustomConversions(converters);
    }
}
