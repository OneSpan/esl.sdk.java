package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IViewSettings extends ISettings{
    public IViewSettings setLayout( LayoutOptions value);
    public LayoutOptions getLayout();
    public IViewSettings setStyle( LayoutStyle value);
    public LayoutStyle getStyle();
    }