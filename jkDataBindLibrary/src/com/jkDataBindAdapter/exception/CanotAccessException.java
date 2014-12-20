package com.jkDataBindAdapter.exception;

import java.lang.reflect.Field;

/**
 * Created by xuejike on 2014/12/20.
 */
public class CanotAccessException extends DataBindException {

    public CanotAccessException(Class c,Field f,Throwable t){
        super(getInfo(c,f.getName()),t);
    }
    public CanotAccessException(Class c,String f,Throwable t){
        super(getInfo(c,f),t);
    }
    private static String getInfo(Class c,String f){
        StringBuilder sb=new StringBuilder("无法访问类：");
        sb.append(c.getName());
        sb.append("中的字段：")
                .append(f)
                .append("的Getter,Setter")

                .append("方法");
        return sb.toString();
    }
}
