package com.jkDataBindUtils.bindUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import com.jkDataBindUtils.annotation.BindDisregard;
import com.jkDataBindUtils.annotation.BindViewId;
import com.jkDataBindUtils.core.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuejike on 2014/12/21.
 */
public class ViewIdBindUtil<T> extends DataBindUtil<T> {
    protected Map<Method, Integer> data2ViewMap;

    protected int layout;
 

    public ViewIdBindUtil(Context context, int layout, Class<T> dataClass) {
        super(context, dataClass);
        this.layout = layout;
    }

    public ViewIdBindUtil(Context context, Class<T> dataClass) {
        super(context, dataClass);
    }

    @Override
    protected void readAnnotationView() {
        if (layout == 0) {
            ReflectUtils.readBindViewLayout(dataClass);
        }
    }

    @Override
    protected View getViewInstance() {
        return View.inflate(context, layout, null);
    }

    @Override
    protected void buildMap() {
//        SimpleAdapter
        data2ViewMap = new HashMap<Method, Integer>();
        Field[] fields = ReflectUtils.getAllFields(dataClass);
        for (Field field : fields) {
            BindDisregard disregard = field.getAnnotation(BindDisregard.class);
            if(disregard == null){
                BindViewId annotation = field.getAnnotation(BindViewId.class);
                if (annotation != null){
                    int value = annotation.value();
                    Method method = ReflectUtils.getGetterMethodByField(field.getName(), dataClass);
                    if (value != 0 && method != null){
                        data2ViewMap.put(method,value);
                    }
                }
            }
        }

    }

    @Override
    protected void fillData2View(T data, View view) {
        Set<Method> methods = data2ViewMap.keySet();
        for (Method method : methods) {
            Object d = ReflectUtils.invokeMethod(method, data);
            bindView(view, d, data2ViewMap.get(method));
        }
    }
    
    


    

    protected void bindView(View view, final Object data, int id) {
        final View v = view.findViewById(id);
        if (v != null) {
            String text = data == null ? "" : data.toString();
            if (text == null) {
                text = "";
            }

            if (v instanceof Checkable) {
                if (data instanceof Boolean) {
                    ((Checkable) v).setChecked((Boolean) data);
                } else if (v instanceof TextView) {
                    setViewText((TextView) v, text);
                }else{
                    throw new IllegalStateException(data.getClass().getName()+"数据无法与"+v.getClass().getName()+"进行绑定");
                }
            } else if (v instanceof TextView) {
                setViewText((TextView) v, text);
            } else if (v instanceof ImageView) {
                if (data instanceof Integer) {
                    setViewImage((ImageView) v, (Integer) data);
                } else if (data instanceof Drawable){
                    setViewImage((ImageView)v,(Drawable) data);
                }else
                {
                    setViewImage((ImageView) v, text);
                }
            } else {
                throw new IllegalStateException(v.getClass().getName() + "无法使用"+ViewIdBindUtil.class.getName()+"绑定");
            }
        }
    }


    public void setViewImage(ImageView v, String value) {
        try {
            v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            v.setImageURI(Uri.parse(value));
        }
    }
    public void setViewImage(ImageView v,Drawable drawable){
        v.setImageDrawable(drawable);
    }

    public void setViewImage(ImageView v, int value) {
        v.setImageResource(value);
    }

    public void setViewText(TextView v, String text) {
        v.setText(text);
    }
}
