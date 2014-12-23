package com.jkBindUtils.bindUtils;

import android.content.Context;
import com.jkBindUtils.core.ReflectUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2014/12/22.
 */
public class ViewPropertyMapBindUtil extends ViewPropertyBindUtil {

    protected Map<String,String> data2ViewNameMap;

    public ViewPropertyMapBindUtil(Context context, Class viewClass, Class dataClass, Map<String, String> data2ViewNameMap) {
        super(context, viewClass, dataClass);
        this.data2ViewNameMap = data2ViewNameMap;
    }

    @Override
    protected void buildMap() {
        super.buildMap();
    }

    @Override
    protected void dataViewMapBuild(Class data, Class view) {

        data2ViewMap =new HashMap<Method,Method>();
        Set<String> set = data2ViewNameMap.keySet();
        for (String s : set) {
            Method getter = ReflectUtil.getGetterMethodByField(s, data);
            if (getter !=null){
                Method setter = ReflectUtil.getSetterMethodByField(data2ViewNameMap.get(s), view, getter.getReturnType());
                if (setter !=null){
                    data2ViewMap.put(getter,setter);
                }
            }

        }


    }
}
