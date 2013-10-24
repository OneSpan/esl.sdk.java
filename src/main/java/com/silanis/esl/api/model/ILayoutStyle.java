package com.silanis.esl.api.model;
//

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