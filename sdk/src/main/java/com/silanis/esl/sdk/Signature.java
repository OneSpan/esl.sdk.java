package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a specific place on a document that a particular signer or sender needs to click on to accept
 */
public class Signature implements Serializable {

    private static final long serialVersionUID = 1L;

    private String signerEmail;
    private int page;
    private double x;
    private double y;
    private SignatureStyle style;
    private double width;
    private double height;

    private Collection<Field> fields = new ArrayList<Field>();
    private String name;
    private boolean extraction;
    private TextAnchor textAnchor;

    private GroupId groupId;

    /**
     * 
     * @param signerEmail
     * @param page
     * @param x
     * @param y
     */
    // TODO: Add constructor with groupID instead of email
    public Signature(String signerEmail, int page, double x, double y) {
        this.signerEmail = signerEmail;
        this.page = page;
        this.x = x;
        this.y = y;
        this.groupId = null;
    }

    public Signature(GroupId groupId, int page, double x, double y ) {
        this.groupId = groupId;
        this.x = x;
        this.y = y;
        this.page = page;
        this.signerEmail = null;
    }

    public String getSignerEmail() {
        return signerEmail;
    }

    public int getPage() {
        return page;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public SignatureStyle getStyle() {
        return style;
    }

    public void setStyle( SignatureStyle style ) {
        this.style = style;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth( double width ) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public Collection<Field> getFields() {
        return fields;
    }

    public void addFields(Collection<Field> fields) {
        this.fields.addAll(fields);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExtraction(boolean extraction) {
        this.extraction = extraction;
    }

    public boolean isExtraction() {
        return extraction;
    }

    public void setTextAnchor( TextAnchor textAnchor ) {
        this.textAnchor = textAnchor;
    }

    public TextAnchor getTextAnchor() {
        return textAnchor;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public boolean isGroupSignature() {
        return groupId != null;
    }
}