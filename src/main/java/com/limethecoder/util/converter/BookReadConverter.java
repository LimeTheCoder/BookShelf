package com.limethecoder.util.converter;

import com.limethecoder.data.domain.*;
import com.limethecoder.data.service.UserService;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ReadingConverter
public class BookReadConverter implements Converter<DBObject, Book> {
    private UserService userService;

    public BookReadConverter() {}

    public BookReadConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Book convert(DBObject source) {
        Book book = new Book();
        book.setId(((ObjectId) source.get("_id")).toString());
        book.setTitle((String) source.get("title"));
        book.setPublishYear((Integer)source.get("publishYear"));
        book.setPagesCnt((Integer)source.get("pagesCnt"));
        book.setRateCnt((Long)source.get("rateCnt"));
        book.setRateValue((Long)source.get("rateValue"));
        book.setCoverUrl((String)source.get("coverUrl"));
        book.setDescription((String)source.get("description"));

        BasicDBList dbList = (BasicDBList)source.get("genres");

        if (dbList != null && !dbList.isEmpty()) {
            List<String> genres = dbList.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            book.setGenres(genres);
        }

        Object publisher = source.get("publisher");
        book.setPublisher(convertToPublisher(publisher));

        dbList = (BasicDBList) source.get("authors");

        if(dbList != null) {
            List<Author> authors = dbList.stream()
                    .map(this::convertToAuthor)
                    .collect(Collectors.toList());
            book.setAuthors(authors);
        }

        dbList = (BasicDBList) source.get("reviews");

        if(dbList != null) {
            List<Review> reviews = dbList.stream()
                    .map(this::convertToReview)
                    .collect(Collectors.toList());
            book.setReviews(reviews);
        }

        return book;
    }

    private Publisher convertToPublisher(Object object) {
        if(object == null) {
            return null;
        }

        DBObject source = (DBObject) object;

        Publisher publisher = new Publisher();
        publisher.setName((String)source.get("name"));

        DBObject addressObject = (DBObject) source.get("address");

        if(addressObject != null) {
            Address address = new Address();
            address.setBuilding((String) addressObject.get("building"));
            address.setStreet((String) addressObject.get("street"));
            address.setCity((String) addressObject.get("city"));
            address.setCountry((String) addressObject.get("country"));
            address.setZip((String) addressObject.get("zip"));

            publisher.setAddress(address);
        }

        return publisher;
    }

    private Author convertToAuthor(Object object) {
        if(object == null) {
            return null;
        }

        DBObject source = (DBObject) object;

        Author author = new Author();
        author.setName((String)source.get("name"));
        author.setSurname((String)source.get("surname"));
        author.setBirthDate((Date)source.get("birthdate"));

        return author;
    }

    private Review convertToReview(Object object) {
        if(object == null) {
            return null;
        }

        DBObject source = (DBObject) object;

        Review review = new Review();
        review.setType((String)source.get("type"));
        review.setText((String)source.get("text"));
        review.setDate((Date)source.get("date"));

        String userLogin = (String)source.get("user");
        if(userLogin != null) {
            User user = userService.findOne(userLogin);
            review.setUser(user);
        }

        return review;
    }
}
