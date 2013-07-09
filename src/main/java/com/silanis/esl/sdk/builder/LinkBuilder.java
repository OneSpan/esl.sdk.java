package com.silanis.esl.sdk.builder;

import com.silanis.awsng.web.rest.model.Link;

/**
 * User: dave
 */
public class LinkBuilder {
    private String text;
    private String tooltip;
    private String href;

    private LinkBuilder( String href ) {
        this.href = href;
        tooltip = null;
        text = null;
    }

    public static LinkBuilder newLink( String href ) {
        return new LinkBuilder( href );
    }

    public LinkBuilder withText( String text ) {
        this.text = text;
        return this;
    }

    public LinkBuilder withTooltip( String title ) {
        this.tooltip = title;
        return this;
    }

    public Link build() {
        Link link = new Link();
        link.setHref( href );
        link.setText( text == null ? href : text );
        link.setTitle( tooltip == null ? href : tooltip );

        return link;
    }
}
