package com.jkDataBindUtils.bindUtils;

import android.content.Context;
import android.view.View;
import com.jkDataBindUtils.core.ReflectUtils;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by xuejike on 2014/12/21.
 */
public class PropertyBindUtil extends DataBindUtil {
    
    protected Class<? extends View> viewClass;
    protected Class dataClass;

    public PropertyBindUtil(Context context, Class<? extends View> viewClass, Class dataClass) {
        super(context);
        this.viewClass = viewClass;
        this.dataClass = dataClass;
        buildMap();
    }

    protected void buildMap(){
        fromToMap = ReflectUtils.getFromToMapByProperty(dataClass,viewClass);
    }

    @Override
    public View createView() {
        return ReflectUtils.createView(viewClass,context);
    }



    @Override
    public View loadData(View view, Object data) {
        Set<Method> keySet = fromToMap.keySet();
        for (Method method : keySet) {
            Object o = ReflectUtils.invokeMethod(method, data);
            ReflectUtils.invokeMethod(fromToMap.get(method),view,o);
        }
        return view;
    }
}
