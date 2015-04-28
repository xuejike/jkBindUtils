package com.jkBindUtils.exception;

/**
 * Created by xuejike on 2014/12/20.
 */
public class NoFindMethodException extends BindUtilsException {
   
    public NoFindMethodException(Class c,String m,Class[] args,Throwable e){
        super(getInfo(c,m,args),e);
        
    }
    protected static String getInfo(Class c, String m,Class[] args){
        StringBuilder sb=new StringBuilder("无法在类：");
        sb.append(c.getName());
        sb.append("中找到方法：")
                .append(m).append("-参数表:");
        for (Class arg : args) {
            if (arg!=null)
                sb.append(arg.getName()+",");
        }
        return sb.toString();
    }
}
