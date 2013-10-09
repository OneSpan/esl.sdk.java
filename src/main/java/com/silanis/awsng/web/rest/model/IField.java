package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IField extends IEntity,IBox{
    public IField setBinding( String value);
    public String getBinding();
    public IField setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IField setExtract( Boolean value);
    public Boolean getExtract();
    public IField setExtractAnchor( ExtractAnchor value);
    public ExtractAnchor getExtractAnchor();
    public IField setHeight( Double value);
    public Double getHeight();
    public IField setId( String value);
    public String getId();
    public IField setLeft( Double value);
    public Double getLeft();
    public IField setName( String value);
    public String getName();
    public IField setPage( Integer value);
    public Integer getPage();
    public IField setSubtype( FieldSubtype value);
    public FieldSubtype getSubtype();
    public IField setTop( Double value);
    public Double getTop();
    public IField setType( FieldType value);
    public FieldType getType();
    public IField setValidation( FieldValidation value);
    public FieldValidation getValidation();
    public IField setValue( String value);
    public String getValue();
    public IField setWidth( Double value);
    public Double getWidth();
    }