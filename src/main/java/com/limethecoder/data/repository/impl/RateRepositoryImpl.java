package com.limethecoder.data.repository.impl;


import com.limethecoder.data.domain.Rate;


public class RateRepositoryImpl extends CustomOperationsRepository<Rate> {
    @Override
    public Class<Rate> getDomainClass() {
        return Rate.class;
    }
}
