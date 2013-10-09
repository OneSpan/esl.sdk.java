package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IResult<T> {
    public IResult<T> setCount( Integer value);
    public Integer getCount();
    public IResult<T> setResults( List<T> value);
    public List<T> getResults();
    }