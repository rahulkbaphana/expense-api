package org.boot.services.expense;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.boot.services.expense.model.Expense;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExpenseBuilder {
    private String id;
    private BigDecimal amount;
    private String category;
    private Clock date;
    private String userId;

    public ExpenseBuilder() {
        this(Clock.systemUTC());
    }

    public ExpenseBuilder(Clock clock) {
        this.date = clock;
    }

    public ExpenseBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ExpenseBuilder amount(BigDecimal amount){
        this.amount = amount;
        return this;
    }

    public ExpenseBuilder category(String category){
        this.category = category;
        return this;
    }

    public ExpenseBuilder userId(String userId){
        this.userId = userId;
        return this;
    }

    public Expense build(){
        Expense expense = new Expense(id, amount, category, userId);
        expense.setUpdatedDateTime(date);
        return expense;
    }

    public String buildAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> obj = new HashMap<String,Object>(){{
            if (id != null) put("id",id);
            if (category != null) put("category",category);
            if (amount != null) put("amount",amount);
            if (userId != null) put("userId",userId);
        }};
        return mapper.writeValueAsString(obj);
    }
}
