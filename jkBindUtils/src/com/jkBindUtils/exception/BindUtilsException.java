package com.jkBindUtils.exception;

/**
 * Created by xuejike on 2014/12/20.
 */
public class BindUtilsException extends RuntimeException {
    public BindUtilsException() {
    }

    public BindUtilsException(String detailMessage) {
        super(detailMessage);
    }

    public BindUtilsException(String detailMessage, Throwable throwable) {
        super(detailMessage+"\n异常原因："+throwable.getCause(), throwable);
    }

    public BindUtilsException(Throwable throwable) {
        super(throwable);
    }

}
