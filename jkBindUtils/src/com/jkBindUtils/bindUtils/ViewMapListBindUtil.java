package com.jkBindUtils.bindUtils;

import android.content.Context;
import android.view.View;
import com.jkBindUtils.core.ReflectUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2014/12/25.
 */
public class ViewMapListBindUtil extends AbsBindUtil<Map<String,Object>> {
    protected Map<String,Method> fieldMethodMap =new HashMap<String, Method>();

    protected Class<? extends View> viewClass;


    public ViewMapListBindUtil(Context context, Class<? extends View> viewClass) {
        super(context);
        this.viewClass = viewClass;
    }

    @Override
    protected void readAnnotationView() {

    }

    @Override
    protected View getViewInstance() {
        return ReflectUtil.createView(viewClass,context);
    }

    @Override
    protected void buildMap() {

    }

    @Override
    protected void fillData2View(Map<String, Object> data, View view) {
        Set<String> set = data.keySet();
        for (String s : set) {
            Object o = data.get(s);
            Method method = getViewMethod(view, s, o.getClass());
            if (method !=null) {
                ReflectUtil.invokeMethod(method, view, o);
            }
        }
    }


    protected Method getViewMethod(View view,String fieldName,Class arg){

        Method method = fieldMethodMap.get(fieldName);
        if (method == null){
            method = ReflectUtil.getSetterMethodByField(fieldName, view.getClass(), arg);
            if (method != null){
                fieldMethodMap.put(fieldName,method);
            }
        }
        return method;
    }
}
