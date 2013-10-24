package com.silanis.esl.api.model;
//

public interface IPrice {
    public IPrice setAmount( Integer value);
    public Integer getAmount();
    public IPrice setCurrency( Currency value);
    public Currency getCurrency();
    }