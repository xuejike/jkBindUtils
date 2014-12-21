package com.jkBindUtils.exception;

import java.lang.reflect.Method;

/**
 * Created by xuejike on 2014/12/20.
 */
public class CannotAccessMethodException extends BindUtilsException {

    public CannotAccessMethodException(Class c, Method m, Throwable e){
        super(getInfo(c,m.getName()),e);
    }
    public CannotAccessMethodException(Class c, String m, Throwable e){
        super(getInfo(c,m),e);
    }
    private static String getInfo(Class c,String m){
        StringBuilder sb=new StringBuilder("无法访问类：");
        sb.append(c.getName());
        sb.append("中的方法：")
                .append(m);

        return sb.toString();
    }
}
