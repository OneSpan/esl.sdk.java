package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ILayoutOptions {
    public ILayoutOptions setBrandingBar( BrandingBarOptions value);
    public BrandingBarOptions getBrandingBar();
    public ILayoutOptions setFooter( FooterOptions value);
    public FooterOptions getFooter();
    public ILayoutOptions setHeader( HeaderOptions value);
    public HeaderOptions getHeader();
    public ILayoutOptions setIframe( Boolean value);
    public Boolean getIframe();
    public ILayoutOptions setNavigator( Boolean value);
    public Boolean getNavigator();
    }