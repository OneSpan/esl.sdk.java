package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICallback {
    public ICallback setEvents( List<CallbackEvent> value);
    public List<CallbackEvent> getEvents();
    public ICallback setUrl( String value);
    public String getUrl();
    }