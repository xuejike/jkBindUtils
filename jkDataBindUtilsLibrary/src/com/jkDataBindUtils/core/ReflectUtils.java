package com.jkDataBindUtils.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.jkDataBindUtils.annotation.BindViewId;
import com.jkDataBindUtils.annotation.BindViewProperty;
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

    /**
     * 给指定的View 对象弹出数据
     * @param view view 对象
     * @param data 数据
     */
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

    public static Map<String,Integer> getPropertyIdMap(Object data){
        Map<String,Integer> map =new HashMap<String, Integer>();
        Field[] fields = getAllField(data);
        for (Field field : fields) {
            BindViewId annotation = field.getAnnotation(BindViewId.class);
            if (annotation != null){
                map.put(field.getName(),annotation.value());
            }
        }
        return map;
    }

    /**
     * 得到所有的字段
     * @param obj
     * @return
     */
    static Field[] getAllField(Object obj){
        Class<?> c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        return fields;
    }

    /**
     * 设置 字段值
     * @param tagObj 目标对象
     * @param fieldName 字段名
     * @param value 值
     * @param fieldClass 字段类型
     * @return
     * @throws InvocationTargetException
     */
    static boolean setField(Object tagObj,String fieldName,Object value,Class fieldClass) throws InvocationTargetException {
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

    /**
     * 设置 指定对象的 字段值
     * @param tagObj  目标对象
     * @param fromField 来源对象的字段
     * @param fromObj 来源对象
     * @throws InvocationTargetException
     */
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

    /**
     * 得到对象中的字段的值
     * @param field  字段
     * @param obj 对象
     * @return
     * @throws InvocationTargetException
     */
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
