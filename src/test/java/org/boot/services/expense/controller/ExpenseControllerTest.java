package org.boot.services.expense.controller;

import org.boot.services.expense.ExpenseBuilder;
import org.boot.services.expense.repository.ExpenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExpenseControllerTest extends BaseControllerTest {

    @Autowired
    private ExpenseRepository repository;

    @Before
    public void setup(){
        repository.deleteAll();
    }

    @Test
    public void shouldSaveExpenseAsInsert() throws Exception {
        ResultActions result = saveAnExpense();

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id",notNullValue()))
                .andExpect(jsonPath("$.message",equalTo("Successfully saved expense.")))
                .andDo(restDoc("insertExpense"));
    }

    @Test
    public void shouldGetAllExpenses() throws Exception {
        saveAnExpense();
        saveAnExpense();

        ResultActions result = mvc.perform(get("/expenses"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    private ResultActions saveAnExpense() throws Exception {
        String payload = new ExpenseBuilder().amount(BigDecimal.TEN).category("food").userId("test-user").buildAsJson();

        return mvc.perform(post("/expense").content(payload).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
    }

}