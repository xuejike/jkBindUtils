package com.jkDataBindUtils.exception;

/**
 * Created by xuejike on 2014/12/20.
 */
public class DataBindException extends RuntimeException {
    public DataBindException() {
    }

    public DataBindException(String detailMessage) {
        super(detailMessage);
    }

    public DataBindException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DataBindException(Throwable throwable) {
        super(throwable);
    }

}
