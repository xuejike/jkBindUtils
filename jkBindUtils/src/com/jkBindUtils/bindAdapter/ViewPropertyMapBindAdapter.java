package com.jkBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.bindUtils.ViewPropertyMapBindUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2014/12/22.
 */
public class ViewPropertyMapBindAdapter extends ViewPropertyBindAdapter {
    private Map<String,String> data2ViewNameMap;

    public ViewPropertyMapBindAdapter(Context context, Class<? extends View> viewClass,  Map<String, String> data2ViewNameMap,List list) {
        super(context, viewClass, list);
        this.data2ViewNameMap = data2ViewNameMap;
    }

    @Override
    protected BindUtil newDataBindUtilInstance(Object data) {
        return new ViewPropertyMapBindUtil(context,viewClass,data.getClass(),data2ViewNameMap);
    }
}
