package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IPlan extends IEntity{
    public IPlan setContract( Cycle value);
    public Cycle getContract();
    public IPlan setCycle( Cycle value);
    public Cycle getCycle();
    public IPlan setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IPlan setDescription( String value);
    public String getDescription();
    public IPlan setFeatures( Map<String, Object> value);
    public Map<String, Object> getFeatures();
    public IPlan setFreeCycles( CycleCount value);
    public CycleCount getFreeCycles();
    public IPlan setGroup( String value);
    public String getGroup();
    public IPlan setId( String value);
    public String getId();
    public IPlan setName( String value);
    public String getName();
    public IPlan setOriginal( String value);
    public String getOriginal();
    public IPlan setPrice( Price value);
    public Price getPrice();
    public IPlan setQuotas( List<Quota> value);
    public List<Quota> getQuotas();
    }