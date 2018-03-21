package org.boot.services.expense.repository;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public class AnyObjectRepository {

    @Autowired
    private MongoTemplate template;

    public <T> Stream<T> find(Query query, Class<T> klass){
        return template.find(query, klass).stream();
    }

    public <T> boolean upsert(String id, Update update, Class<T> klass) {
        Query idQuery = new Query().addCriteria(Criteria.where("id").is(id));
        WriteResult result = template.upsert(idQuery, update, klass);
        return result.getN() == 1;
    }

}
