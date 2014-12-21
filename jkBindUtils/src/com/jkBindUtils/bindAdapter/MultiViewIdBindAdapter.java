package com.jkBindUtils.bindAdapter;

import android.content.Context;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.bindUtils.ViewIdBindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/21.
 */
public class MultiViewIdBindAdapter extends MultiViewBindAdapter {
    public MultiViewIdBindAdapter(Context context, int maxViewTypeNum, List list) {
        super(context, maxViewTypeNum, list);
    }

    @Override
    protected BindUtil newDataBindUtilInstance(Object data) {
        return new ViewIdBindUtil(context,data.getClass());
    }
}
