package com.jkDataBindUtils.bindUtils;

import android.content.Context;
import android.view.View;
import com.jkDataBindUtils.annotation.BindDisregard;
import com.jkDataBindUtils.annotation.BindViewProperty;
import com.jkDataBindUtils.core.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2014/12/21.
 */
public class ViewPropertyBindUtil<T> extends DataBindUtil<T> {
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
            Object o = ReflectUtils.invokeMethod(method, data);
            ReflectUtils.invokeMethod(data2ViewMap.get(method),view,o);
        }
    }



    @Override
    protected void readAnnotationView() {
        if (viewClass == null){
            viewClass = ReflectUtils.readBindViewClass(dataClass);
        }
    }

    @Override
    protected View getViewInstance() {
        return ReflectUtils.createView(viewClass,context);
    }

    protected  void dataViewMapBuild(Class data, Class view){
        data2ViewMap = new HashMap<Method, Method>();
      //  view2DataMap = new HashMap<Method, Method>();
        
        Field[] fields = ReflectUtils.getAllFields(data);
        for (Field field : fields) {

            BindDisregard disregard = field.getAnnotation(BindDisregard.class);

            if (disregard == null){
                BindViewProperty annotation = field.getAnnotation(BindViewProperty.class);
                String fromFN = field.getName();
                String toFN = fromFN;

                if (annotation != null){
                    toFN = annotation.value();
                }
                Method dataM = ReflectUtils.getGetterMethodByField(fromFN, data);
                Method viewM = ReflectUtils.getSetterMethodByField(toFN, view, field.getType());
                if (dataM != null && viewM != null) {
                    data2ViewMap.put(dataM, viewM);
                  //  view2DataMap.put(viewM,dataM);
                }
            }
        }

    }

}
