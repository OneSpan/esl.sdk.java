package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IBox {
    public IBox setHeight( Double value);
    public Double getHeight();
    public IBox setLeft( Double value);
    public Double getLeft();
    public IBox setTop( Double value);
    public Double getTop();
    public IBox setWidth( Double value);
    public Double getWidth();
    }