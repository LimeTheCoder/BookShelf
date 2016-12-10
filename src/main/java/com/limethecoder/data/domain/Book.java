package com.limethecoder.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public class Book {
    @Id
    private String id;

    private String title;

    private String description;

    private int pagesCnt;

    private int publishYear;

    private String coverUrl;

    private Publisher publisher;

    private List<Author> authors;

    private List<Review> reviews;

    private long rateCnt;

    private long rateValue;

    private List<String> genres;

    public long getRateCnt() {
        return rateCnt;
    }

    public void setRateCnt(long rateCnt) {
        this.rateCnt = rateCnt;
    }

    public long getRateValue() {
        return rateValue;
    }

    public void setRateValue(long rateValue) {
        this.rateValue = rateValue;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPagesCnt() {
        return pagesCnt;
    }

    public void setPagesCnt(int pagesCnt) {
        this.pagesCnt = pagesCnt;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return title;
    }
}
