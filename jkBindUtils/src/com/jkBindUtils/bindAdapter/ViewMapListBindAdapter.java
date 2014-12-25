package com.jkBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.bindUtils.ViewMapListBindUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2014/12/25.
 */
public class ViewMapListBindAdapter<T extends Map<String,Object>> extends ViewPropertyBindAdapter<T> {


    public ViewMapListBindAdapter(Context context, Class<? extends View> viewClass, List list) {
        super(context, viewClass, list);
    }

    @Override
    protected BindUtil newDataBindUtilInstance(T data) {
        return new ViewMapListBindUtil(context,viewClass);
    }
}
