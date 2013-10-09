package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ISignedDocumentDelivery {
    public ISignedDocumentDelivery setDestinations( List<External> value);
    public List<External> getDestinations();
    public ISignedDocumentDelivery setExcludedDocuments( List<Document> value);
    public List<Document> getExcludedDocuments();
    public ISignedDocumentDelivery setFilePrefix( String value);
    public String getFilePrefix();
    public ISignedDocumentDelivery setFileSuffix( String value);
    public String getFileSuffix();
    }