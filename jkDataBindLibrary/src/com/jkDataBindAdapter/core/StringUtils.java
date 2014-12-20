package com.jkDataBindAdapter.core;

/**
 * Created by xuejike on 2014/12/20.
 */
public class StringUtils {
    public static String toFirstUpperCase(String s){
        char[] chars = s.toCharArray();
        chars[0] -= 32;
        return new String(chars);
    }
    public static String toFirstLowerCase(String s){
        char[] chars = s.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    public static String getSetterMethodName(String fieldName){
        return "set"+toFirstUpperCase(fieldName);
    }
    public static String getGetterMethodName(String fieldName){
        return "get"+toFirstUpperCase(fieldName);
    }
    public static String getGetterMethodNameBoolean(String fieldName){
        return "is"+toFirstUpperCase(fieldName);
    }

}
