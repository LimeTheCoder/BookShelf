package com.limethecoder.data.repository;


import com.limethecoder.data.domain.Like;
import com.limethecoder.data.repository.interfaces.CustomOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like, String>, CustomOperations<Like> {
}
