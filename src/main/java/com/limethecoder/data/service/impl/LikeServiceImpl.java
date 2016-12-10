package com.limethecoder.data.service.impl;

import com.limethecoder.data.domain.Like;
import com.limethecoder.data.repository.LikeRepository;
import com.limethecoder.data.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl extends AbstractMongoService<Like, String>
        implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Like findLike(String userId, String bookId) {
        return likeRepository.findOne(userId, bookId);
    }

    @Override
    public boolean isLiked(String userId, String bookId) {
        return likeRepository.exists(userId, bookId);
    }

    @Override
    public void delete(String userId, String bookId) {
        likeRepository.delete(userId, bookId);
    }

    @Override
    public Like like(String userId, String bookId) {
        return likeRepository.insert(new Like(userId, bookId));
    }

    @Override
    protected MongoRepository<Like, String> getRepository() {
        return likeRepository;
    }
}
