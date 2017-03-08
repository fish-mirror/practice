package com.zjicm.common.sql.exception;

public class DAOWriteException extends RuntimeException {
    private static final long serialVersionUID = -5536559604690561155L;

    public DAOWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOWriteException(Throwable cause) {
        super(cause);
    }
}
