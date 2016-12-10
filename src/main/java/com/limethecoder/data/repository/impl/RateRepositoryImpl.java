package com.limethecoder.data.repository.impl;


import com.limethecoder.data.domain.Rate;
import com.limethecoder.data.repository.interfaces.CustomOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.*;


public class RateRepositoryImpl extends CustomOperationsRepository<Rate> {
    @Override
    public Class<Rate> getDomainClass() {
        return Rate.class;
    }
}
