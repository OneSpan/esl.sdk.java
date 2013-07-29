package com.silanis.esl.sdk;

import java.util.Iterator;
import java.util.List;

/**
 * User: dave
 */
public class Page<T> implements Iterable<T> {
    private final PageRequest request;
    private int totalElements;
    private List<T> results;

    public Page( List<T> results, int totalElements, PageRequest request ) {
        this.request = request;
        this.totalElements = totalElements;
        this.results = results;
    }

    public int getSize() {
        return request.getPageSize();
    }

    public int getNumberOfElements() {
        return results.size();
    }

    public int getTotalElements() {
        return totalElements;
    }

    public boolean hasNextPage() {
        return (request.getFrom() + request.getPageSize() ) <= totalElements;
    }

    public PageRequest getNextRequest() {
        return hasNextPage() ? request.next() : null ;
    }

    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}


