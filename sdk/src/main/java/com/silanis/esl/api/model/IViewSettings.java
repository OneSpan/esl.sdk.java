package com.silanis.esl.api.model;
//

public interface IViewSettings extends ISettings{
    public IViewSettings setLayout( LayoutOptions value);
    public LayoutOptions getLayout();
    public IViewSettings setStyle( LayoutStyle value);
    public LayoutStyle getStyle();
    }