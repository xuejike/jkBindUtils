package com.jkBindUtils.exception;

/**
 * Created by xuejike on 2014/12/21.
 */
public class CannotNewInstanceException extends BindUtilsException {
    public CannotNewInstanceException(Class c, Object[] args, Throwable throwable) {
        super(getInfo(c,args), throwable);
    }
    protected static String getInfo(Class c,Object[] args){
        StringBuilder sb=new StringBuilder("无法创建类：");
        sb.append(c.getName()).append("\n构造方法参数：");
        for (Object arg : args) {
            sb.append(arg.getClass()+",");
        }
        sb.append("\n参数值：");
        for (Object arg : args) {
            sb.append(arg+",");
        }
        return sb.toString();


    }
}
