package com.jkDataBindAdapter.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.jkDataBindAdapter.annotation.BindViewProperty;
import com.jkDataBindAdapter.exception.CanotAccessException;
import com.jkDataBindAdapter.exception.DataBindException;
import com.jkDataBindAdapter.exception.NoFindGetterSetterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ReflectUtils {
    private static final String TAG = "ReflectUtils";
    public static View createView(Class<? extends View> c,Context context){
        try {
            Constructor<? extends View> constructor = c.getConstructor(Context.class);
            return  constructor.newInstance(context);
        } catch (Exception e) {
            int i = DataBindConfig.debugLevel.ordinal();
            if ( e instanceof DataBindException){
                if (i > 0){
                    Log.d(TAG,e.getMessage());
                }
                if (i > 1){
                    e.printStackTrace();
                }
            }else{
                e.printStackTrace();
            }

            return null;
        }
    }
    public static void loadData(View view,Object data){
        if (view == null) return;
        Field[] fields = getAllField(data);
        try {
            for (Field field : fields) {
                setField(view,field,data);
            }
        }catch (Exception e){
            int i = DataBindConfig.debugLevel.ordinal();
            if ( e instanceof DataBindException){
                if (i > 0){
                    Log.d(TAG,e.getMessage());
                }
                if (i > 1){
                    e.printStackTrace();
                }
            }else{
                e.printStackTrace();
            }

        }

    }



    protected static Field[] getAllField(Object obj){
        Class<?> c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        return fields;
    }
    protected static boolean setField(Object tagObj,String fieldName,Object value,Class fieldClass) throws InvocationTargetException {
        Method method;
        try {
             method = tagObj.getClass().getMethod(StringUtils.getSetterMethodName(fieldName),fieldClass);
            method.invoke(tagObj,value);
            return true;
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
            throw new NoFindGetterSetterException(tagObj.getClass(),fieldName,e);
        }  catch (IllegalAccessException e) {
           // e.printStackTrace();
            throw new CanotAccessException(tagObj.getClass(),fieldName,e);
        }

    }

    protected static void setField(Object tagObj,Field fromField,Object fromObj) throws InvocationTargetException {
        BindViewProperty bindViewProperty = fromField.getAnnotation(BindViewProperty.class);
        String fieldName;
        Object fieldValue = getFieldValue(fromField,fromObj);
        if (bindViewProperty != null){
            fieldName = bindViewProperty.value();
        }else{
            fieldName = fromField.getName();
        }
        setField(tagObj,fieldName,fieldValue,fromField.getType());
    }

    protected static void setFieldValue(String fieldName,Object obj,Object value,Class valueClass) throws InvocationTargetException {
        String methodName = StringUtils.getSetterMethodName(fieldName);
        try {
            Method method = obj.getClass().getMethod(methodName, valueClass);
            method.invoke(obj,value);
        } catch (NoSuchMethodException e) {
            throw new NoFindGetterSetterException(obj.getClass(),fieldName,e);
        } catch (IllegalAccessException e) {
            throw new CanotAccessException(obj.getClass(),fieldName,e);
        }
    }
    protected static Object getFieldValue(Field field,Object obj) throws InvocationTargetException {
        Method method = null;
        try {
             method = obj.getClass().getMethod(StringUtils.getGetterMethodName(field.getName()));
        } catch (NoSuchMethodException e) {
            try {
                method = obj.getClass().getMethod(StringUtils.getGetterMethodNameBoolean(field.getName()));
            } catch (NoSuchMethodException e1) {
                throw new NoFindGetterSetterException(obj.getClass(),field,e1);
            }
        }
        try {
            return method.invoke(obj);

        } catch (IllegalAccessException e) {
            throw new CanotAccessException(obj.getClass(),field,e);
        }

    }





}
