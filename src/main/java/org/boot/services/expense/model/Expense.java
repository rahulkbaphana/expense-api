package org.boot.services.expense.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Document(collection = "expense")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    private ObjectId id;

    @NotNull
    @Indexed
    private BigDecimal amount;

    @NotNull @Indexed private String category;

    @Indexed private LocalDateTime date = LocalDateTime.now();

    @Indexed private String userId;

    public Expense(String id, BigDecimal amount, String category, String userId) {
        if(id!=null) this.id = new ObjectId(id);
        this.amount = amount;
        this.category = category;
        this.userId = userId;
    }

    public void setUpdatedDateTime(Clock date) {
        this.date = LocalDateTime.now(date);
    }
}
