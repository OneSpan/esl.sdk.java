package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ILayoutStyle {
    public ILayoutStyle setBrandingBar( Image value);
    public Image getBrandingBar();
    public ILayoutStyle setDialog( Style value);
    public Style getDialog();
    public ILayoutStyle setTitleBar( Style value);
    public Style getTitleBar();
    public ILayoutStyle setToolbar( Style value);
    public Style getToolbar();
    }