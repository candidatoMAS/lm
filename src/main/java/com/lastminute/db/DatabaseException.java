package com.lastminute.db;

public class DatabaseException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public DatabaseException() {}
    
    public DatabaseException(Exception e) {
        super(e);
    }

}
