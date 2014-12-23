package com.jkBindUtils.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.exception.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
@SuppressWarnings("unchecked")
public class ReflectUtil {
    private static final String TAG = "ReflectUtils";

    /**
     * 通过反射创建 View 对象
     * @param c view 的class对象
     * @param context android 上下文
     * @return view对象
     */
    public static View createView(Class<? extends View> c,Context context){
       
        View v = null;
        Constructor<? extends View> constructor =null;
        try {
            constructor = c.getConstructor(Context.class);
            v =  constructor.newInstance(context);
        } catch (InstantiationException e) {
            dealWithException(new CannotNewInstanceException(c,null,e));
        } catch (IllegalAccessException e) {
            dealWithException(new CannotAccessMethodException(c,c.getName(),e));
        } catch (InvocationTargetException e) {
            dealWithException(e);
        } catch (NoSuchMethodException e) {
            dealWithException(e);
        }
        return v;
    }
    public static<T> T createData(Class<T> dataClass){
        try {
            return dataClass.newInstance();
        } catch (InstantiationException e) {
            dealWithException(new CannotNewInstanceException(dataClass,new Object[]{},e));
        } catch (IllegalAccessException e) {
            dealWithException(e);
        }
        return null;

    }
    

    
    
    public static Method getGetterMethodByField(String fieldName,Class c){
        Method m = null;
        try {
            m = c.getMethod(StringUtil.getGetterMethodName(fieldName));
        } catch (NoSuchMethodException e) {
            try {
                m = c.getMethod(StringUtil.getGetterMethodNameBoolean(fieldName));
            } catch (NoSuchMethodException e1) {
                dealWithException(new NoFindMethodException(c, StringUtil.getGetterMethodName(fieldName),new Class[]{},e));
            }
        }
        return m;
    }
    public static Method getSetterMethodByField(String fieldName,Class c,Class arg){
        Method m = null;
        String methodName = StringUtil.getSetterMethodName(fieldName);

        try {
            //找自己
            m = c.getMethod(methodName,arg);
            return m;
        } catch (NoSuchMethodException e) {
            dealWithException(new NoFindMethodException(c,methodName,new Class[]{arg},e));
        }
        //找接口
        Class[] interfaces = arg.getInterfaces();
        for (Class aClass : interfaces) {
            try {
                m = c.getMethod(methodName,aClass);
                return m;
            } catch (NoSuchMethodException e) {
                dealWithException(new NoFindMethodException(c,methodName,new Class[]{aClass},e));
            }
        }


//        找父类
        Class<?> superclass = arg;
        while (!superclass.equals(Object.class)){
            try {
                m = c.getMethod(methodName,superclass);
                return m;
            } catch (NoSuchMethodException e) {
                dealWithException(new NoFindMethodException(c,methodName,new Class[]{superclass},e));
            }
            superclass= superclass.getSuperclass();
        }
        return m;
    }
    public static Object invokeMethod(Method method,Object obj,Object... arg){
        Object result = null;
        try {
            result = method.invoke(obj,arg);
        } catch (IllegalAccessException e) {
            dealWithException(new CannotAccessMethodException(obj.getClass(),method,e));
        } catch (InvocationTargetException e) {
            dealWithException(new CannotInvokeMethodException(obj,method,arg,e));
        }
        return result;

    }
    public static List<Field> getAllFields(Class c){
        List<Field> list =new ArrayList<Field>();
        do{
            Field[] fields = c.getDeclaredFields();
            Collections.addAll(list, fields);
            
        }while ((c=c.getSuperclass()) != Object.class);
        return list;
        
    }

    private static void dealWithException(Exception e){
        

        
        int ordinal = BindUtilConfig.debugLevel.ordinal();
        if (ordinal > 0){
            if (e instanceof BindUtilsException) {
                Log.i(TAG, e.getMessage());
            }else{
                throw  new BindUtilsException(e);
            }
            
        }
        if (ordinal > 1){
            if (!(e instanceof NoFindMethodException)){
                throw  (BindUtilsException)e;
            }
        }
        if (ordinal > 2){
            throw  new BindUtilsException(e);
        }
    }
    
    public static Class<? extends View> readBindViewClass(Class dataClass){
        BindView annotation = (BindView) dataClass.getAnnotation(BindView.class);
        if (annotation == null) return null;
        Class<? extends View> vc = annotation.viewClass();
        if (annotation.viewClass() == View.class) vc = null;
        return vc;
    }
    public static int readBindViewLayout(Class dataClass){
        BindView annotation = (BindView) dataClass.getAnnotation(BindView.class);
        if (annotation == null) return 0;
        return annotation.layout();
    }

}
