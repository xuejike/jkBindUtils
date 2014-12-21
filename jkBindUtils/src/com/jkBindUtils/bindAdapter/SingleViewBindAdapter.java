package com.jkBindUtils.bindAdapter;

import android.content.Context;
import com.jkBindUtils.bindUtils.BindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/21.
 */
public abstract class SingleViewBindAdapter extends ViewBindAdapter {
    protected BindUtil bindUtil;

    public SingleViewBindAdapter(Context context, List list) {
        super(context,list);
    }


    @Override
    protected BindUtil getDataBindUtil(Object data) {
        if (bindUtil == null){
            bindUtil = newDataBindUtilInstance(data);
        }
        return bindUtil;
    }
    protected abstract BindUtil newDataBindUtilInstance(Object data);
}
