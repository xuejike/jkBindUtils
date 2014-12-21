package com.jkDataBindUtils.bindUtils;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by xuejike on 2014/12/21.
 */
public abstract class DataBindUtil {
    
    protected Map<Method,Method> fromToMap;
    protected Context context;

    protected DataBindUtil(Context context) {
        this.context = context;
    }

    public View createView(Object data) {
        View v = createView();
        loadData(v,data);
        return v;
    }
    public abstract View createView();
    public abstract View loadData(View view, Object data);

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
