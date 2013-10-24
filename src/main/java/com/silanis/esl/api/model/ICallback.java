package com.silanis.esl.api.model;
//
import java.util.List;

public interface ICallback {
    public ICallback setEvents( List<CallbackEvent> value);
    public List<CallbackEvent> getEvents();
    public ICallback setUrl( String value);
    public String getUrl();
    }