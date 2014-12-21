package com.jkBindUtils.bindUtils;

import android.content.Context;
import android.view.View;
import com.jkBindUtils.annotation.BindDisregard;
import com.jkBindUtils.annotation.BindViewProperty;
import com.jkBindUtils.core.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2014/12/21.
 */
public class ViewPropertyBindUtil<T> extends AbsBindUtil<T> {
    protected Map<Method,Method> data2ViewMap;
    protected Map<Method,Method> view2DataMap;
    protected Class<? extends View> viewClass = null;

    public ViewPropertyBindUtil(Context context, Class<? extends View> viewClass, Class<T> dataClass) {
        super(context);
        this.viewClass = viewClass;
        this.dataClass = dataClass;
        buildMap();
    }

    public ViewPropertyBindUtil(Context context, Class<T> dataClass) {
        super(context);
        this.dataClass = dataClass;
    }


    @Override
    protected void buildMap(){
        dataViewMapBuild(dataClass, viewClass);
    }

    @Override
    public void fillData2View(T data,View view) {
        Set<Method> keySet = data2ViewMap.keySet();
        for (Method method : keySet) {
            Object o = ReflectUtil.invokeMethod(method, data);
            ReflectUtil.invokeMethod(data2ViewMap.get(method), view, o);
        }
    }



    @Override
    protected void readAnnotationView() {
        if (viewClass == null){
            viewClass = ReflectUtil.readBindViewClass(dataClass);
        }
    }

    @Override
    protected View getViewInstance() {
        return ReflectUtil.createView(viewClass, context);
    }

    protected  void dataViewMapBuild(Class data, Class view){
        data2ViewMap = new HashMap<Method, Method>();
      //  view2DataMap = new HashMap<Method, Method>();
        
        List<Field> fields = ReflectUtil.getAllFields(data);
        for (Field field : fields) {

            BindDisregard disregard = field.getAnnotation(BindDisregard.class);

            if (disregard == null){
                BindViewProperty annotation = field.getAnnotation(BindViewProperty.class);
                String fromFN = field.getName();
                String toFN = fromFN;

                if (annotation != null){
                    toFN = annotation.value();
                }
                Method dataM = ReflectUtil.getGetterMethodByField(fromFN, data);
                Method viewM = ReflectUtil.getSetterMethodByField(toFN, view, field.getType());
                if (dataM != null && viewM != null) {
                    data2ViewMap.put(dataM, viewM);
                  //  view2DataMap.put(viewM,dataM);
                }
            }
        }

    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<? extends View> viewClass) {
        this.viewClass = viewClass;
    }
}
