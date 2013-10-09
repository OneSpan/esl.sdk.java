package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IPrice {
    public IPrice setAmount( Integer value);
    public Integer getAmount();
    public IPrice setCurrency( Currency value);
    public Currency getCurrency();
    }