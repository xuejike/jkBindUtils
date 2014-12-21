package com.jkDataBindUtils.exception;

import java.lang.reflect.Field;

/**
 * Created by xuejike on 2014/12/20.
 */
public class NoFindGetterSetterException extends DataBindException {
    public NoFindGetterSetterException() {
    }

    public NoFindGetterSetterException(String detailMessage) {
        super(detailMessage);
    }

    public NoFindGetterSetterException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NoFindGetterSetterException(Throwable throwable) {
        super(throwable);
    }

    public NoFindGetterSetterException(Class c, Field f, Throwable throwable){
        super(getNoFindInfo(c, f.getName(), "Getter,Setter"),throwable);
    }
    public NoFindGetterSetterException(Class c, String f, Throwable throwable){
        super(getNoFindInfo(c, f, "Getter,Setter"),throwable);
    }
    protected static String getNoFindInfo(Class c, String f, String msg){
        StringBuilder sb=new StringBuilder("无法在类：");
        sb.append(c.getName());
        sb.append("中找到字段：")
                .append(f)
                .append("的")
                .append(msg)
                .append("方法");
        return sb.toString();
    }
}
