package com.silanis.esl.api.model;
//
import java.util.List;

public interface IResult<T> {
    public IResult<T> setCount( Integer value);
    public Integer getCount();
    public IResult<T> setResults( List<T> value);
    public List<T> getResults();
    }