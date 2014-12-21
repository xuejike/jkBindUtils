package com.jkDataBindUtils.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.jkDataBindUtils.annotation.BindView;
import com.jkDataBindUtils.exception.DataBindException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ReflectUtils {
    private static final String TAG = "ReflectUtils";

    /**
     * 通过反射创建 View 对象
     * @param c view 的class对象
     * @param context android 上下文
     * @return view对象
     */
    public static View createView(Class<? extends View> c,Context context){
       
        View v = null;
        try {
            Constructor<? extends View> constructor = c.getConstructor(Context.class);
            v =  constructor.newInstance(context);
        } catch (InstantiationException e) {
            dealWithException(e);
        } catch (IllegalAccessException e) {
            dealWithException(e);
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
            dealWithException(e);
        } catch (IllegalAccessException e) {
            dealWithException(e);
        }
        return null;

    }
    

    
    
    public static Method getGetterMethodByField(String fieldName,Class c){
        Method m = null;
        try {
            m = c.getMethod(StringUtils.getGetterMethodName(fieldName));
        } catch (NoSuchMethodException e) {
            try {
                m = c.getMethod(StringUtils.getGetterMethodNameBoolean(fieldName));
            } catch (NoSuchMethodException e1) {
                dealWithException(e);
            }
        }
        return m;
    }
    public static Method getSetterMethodByField(String fieldName,Class c,Class arg){
        Method m = null;
        
        
        try {
            //找自己
            m = c.getMethod(StringUtils.getSetterMethodName(fieldName),arg);
            return m;
        } catch (NoSuchMethodException e) {
            dealWithException(e);
        }
        //找接口
        Class[] interfaces = arg.getInterfaces();
        for (Class aClass : interfaces) {
            try {
                m = c.getMethod(StringUtils.getSetterMethodName(fieldName),aClass);
                return m;
            } catch (NoSuchMethodException e) {
                dealWithException(e);
            }
        }


//        找父类
        Class superclass = arg;
        while (!superclass.equals(Object.class)){
            try {
                m = c.getMethod(StringUtils.getSetterMethodName(fieldName),superclass);
                return m;
            } catch (NoSuchMethodException e) {
                dealWithException(e);
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
            dealWithException(e);
        } catch (InvocationTargetException e) {
            dealWithException(e);
        }
        return result;

    }
    public static Field[] getAllFields(Class c){
        return c.getDeclaredFields();
        
    }

    private static void dealWithException(Exception e){
        int ordinal = DataBindConfig.debugLevel.ordinal();
        if (ordinal > 0){
            Log.d(TAG,e.getMessage());
        }
        if (ordinal > 1){
            e.printStackTrace();
        }
        if (ordinal > 2){
            throw  new DataBindException(e);
        }
    }
    
    public static Class readBindViewClass(Class dataClass){
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
