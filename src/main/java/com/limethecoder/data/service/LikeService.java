package com.limethecoder.data.service;


import com.limethecoder.data.domain.Like;

public interface LikeService extends Service<Like,String> {
    Like findLike(String userId, String bookId);
    boolean isLiked(String userId, String bookId);
    void delete(String userId, String bookId);
    Like like(String userId, String bookId);
}
