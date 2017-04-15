package com.zjicm.common.lang.sql.exception;

public class DAOReadException extends RuntimeException {
    private static final long serialVersionUID = -5690921209103098786L;

    public DAOReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOReadException(Throwable cause) {
        super(cause);
    }
}
