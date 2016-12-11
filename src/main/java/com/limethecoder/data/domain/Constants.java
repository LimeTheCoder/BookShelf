package com.limethecoder.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Constants {
    @Id
    private String id;

    private List<String> genres;

    private List<String> reviewTypes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getReviewTypes() {
        return reviewTypes;
    }

    public void setReviewTypes(List<String> reviewTypes) {
        this.reviewTypes = reviewTypes;
    }
}
