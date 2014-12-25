package com.jkBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.bindUtils.ViewPropertyBindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ViewPropertyBindAdapter<T> extends SingleViewBindAdapter<T> {


    protected Class<? extends View> viewClass =null;

    public ViewPropertyBindAdapter(Context context, List<T> list) {
        super(context, list);
    }

    public ViewPropertyBindAdapter(Context context, Class<? extends View> viewClass, List list) {
        super(context, list);
        this.viewClass = viewClass;
    }

    @Override
    protected BindUtil newDataBindUtilInstance(T data) {
        if (viewClass == null){
            return new ViewPropertyBindUtil(context,data.getClass());
        }else{
            return new ViewPropertyBindUtil(context,viewClass,data.getClass());
        }
    }
}
