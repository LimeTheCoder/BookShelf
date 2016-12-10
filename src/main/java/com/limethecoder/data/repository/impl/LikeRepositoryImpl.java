package com.limethecoder.data.repository.impl;


import com.limethecoder.data.domain.Like;


public class LikeRepositoryImpl extends CustomOperationsRepository<Like> {
    @Override
    public Class<Like> getDomainClass() {
        return Like.class;
    }
}
