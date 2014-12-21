package com.jkBindUtils.exception;

import java.lang.reflect.Method;

/**
 * Created by xuejike on 2014/12/21.
 */
public class CannotInvokeMethodException extends BindUtilsException {
    public CannotInvokeMethodException(Object obj, Method method, Object[] args, Throwable e){
        super(getInfo(obj, method, args),e);
        
    }
    private static String getInfo(Object obj,Method method,Object[] args){
        StringBuilder sb=new StringBuilder("无法调用");
        sb.append(obj.getClass().getName())
                .append("的")
                .append(method.getName())
                .append("方法\n");
        sb.append("详细信息：\n")
                .append("\t对象：").append(obj).append("\n")
                .append("\t参数：");
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        return sb.toString();


    }
}
