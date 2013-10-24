package com.silanis.esl.api.model;
//
import java.util.List;
import java.util.Map;

public interface ILayout extends IBaseTemplate{
    public ILayout setAutocomplete( Boolean value);
    public Boolean getAutocomplete();
    public ILayout setConsent( String value);
    public String getConsent();
    public ILayout setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public ILayout setDescription( String value);
    public String getDescription();
    public ILayout setDocuments( List<Document> value);
    public List<Document> getDocuments();
    public ILayout setDue( java.util.Date value);
    public java.util.Date getDue();
    public ILayout setEmailMessage( String value);
    public String getEmailMessage();
    public ILayout setId( String value);
    public String getId();
    public ILayout setLanguage( String value);
    public String getLanguage();
    public ILayout setLimits( PackageArtifactsLimits value);
    public PackageArtifactsLimits getLimits();
    public ILayout setMessages( List<Message> value);
    public List<Message> getMessages();
    public ILayout setName( String value);
    public String getName();
    public ILayout setRoles( List<Role> value);
    public List<Role> getRoles();
    public ILayout setSender( Sender value);
    public Sender getSender();
    public ILayout setSettings( PackageSettings value);
    public PackageSettings getSettings();
    public ILayout setSignedDocumentDelivery( SignedDocumentDelivery value);
    public SignedDocumentDelivery getSignedDocumentDelivery();
    public ILayout setStatus( PackageStatus value);
    public PackageStatus getStatus();
    public ILayout setType( BasePackageType value);
    public BasePackageType getType();
    public ILayout setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    public ILayout setVisibility( Visibility value);
    public Visibility getVisibility();
    }