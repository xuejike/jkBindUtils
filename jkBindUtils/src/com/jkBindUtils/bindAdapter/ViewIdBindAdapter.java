package com.jkBindUtils.bindAdapter;

import android.content.Context;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.bindUtils.ViewIdBindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ViewIdBindAdapter extends SingleViewBindAdapter {
    private int layout;

    public ViewIdBindAdapter(Context context, int layout, List list) {
        super(context, list);
        this.layout = layout;
    }

    public ViewIdBindAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected BindUtil newDataBindUtilInstance(Object data) {
        return new ViewIdBindUtil(context,layout,data.getClass());
    }
}
