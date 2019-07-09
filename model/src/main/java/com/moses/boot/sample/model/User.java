package com.moses.boot.sample.model;

import com.moses.boot.sample.validation.constraints.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class User {
    @NotNull
    @Max(value=10000)
    private Integer id;

    @NotNull
    private String name;

    //卡号 - CARD-123456789
    @NotNull
    @ValidCardNumber
    private String cardNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
