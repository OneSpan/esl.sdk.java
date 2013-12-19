package com.silanis.esl.sdk;

/**
 * User: dave
 */
public class PageRequest {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 50;

    private int from;
    private int pageSize;

    public PageRequest( int from ) {
        this( from, DEFAULT_PAGE_SIZE );
    }

    public PageRequest( int from, int pageSize ) {
        this.from = from;
        this.pageSize = pageSize;
    }

    public PageRequest next() {
        return new PageRequest( to() + 1, pageSize );
    }

    public int to() {
        return from + pageSize - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getFrom() {
        return from;
    }
}
