package com.silanis.esl.api.model;
//
import java.util.List;

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