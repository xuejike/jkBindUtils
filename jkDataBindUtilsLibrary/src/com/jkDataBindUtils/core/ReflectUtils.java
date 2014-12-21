package com.jkDataBindUtils.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.jkDataBindUtils.annotation.BindDisregard;
import com.jkDataBindUtils.annotation.BindViewId;
import com.jkDataBindUtils.annotation.BindViewProperty;
import com.jkDataBindUtils.bindUtils.DataBindUtil;
import com.jkDataBindUtils.exception.CanotAccessException;
import com.jkDataBindUtils.exception.DataBindException;
import com.jkDataBindUtils.exception.NoFindGetterSetterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<Method,Method> getFromToMapByProperty(Class fromData,Class toView){
        Map<Method,Method> map = new HashMap<Method, Method>();
        Field[] fields = getAllFields(fromData);
        for (Field field : fields) {
            
            BindDisregard disregard = field.getAnnotation(BindDisregard.class);
            
            if (disregard == null){
                BindViewProperty annotation = field.getAnnotation(BindViewProperty.class);
                String fromFN = field.getName();
                String toFN = fromFN;
                
                if (annotation != null){
                    toFN = annotation.value();
                }
                Method fromM = getGetterMethodByField(fromFN, fromData);
                Method toM = getSetterMethodByField(toFN, toView, field.getType());
                if (fromM != null && toM != null) {
                    map.put(fromM, toM);
                }
            }

        }
        return map;
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
    private static void dealWithException(InvocationTargetException e){
      
    }
    private static void dealWithException(IllegalAccessException e){

    }
    private static void dealWithException(NoSuchMethodException e){

    }
    private static void dealWithException(InstantiationException e){

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

}
