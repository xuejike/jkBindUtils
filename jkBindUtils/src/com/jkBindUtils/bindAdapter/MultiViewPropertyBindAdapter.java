package com.jkBindUtils.bindAdapter;

import android.content.Context;
import com.jkBindUtils.bindUtils.AbsBindUtil;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.bindUtils.ViewPropertyBindUtil;

import java.util.*;

/**
 * Created by xuejike on 2014/12/21.
 */
public class MultiViewPropertyBindAdapter extends MultiViewBindAdapter {

    public MultiViewPropertyBindAdapter( Context context,List list,int maxViewTypeNum) {
        super(context, maxViewTypeNum, list);

    }


    @Override
    protected BindUtil newDataBindUtilInstance(Object data) {
        AbsBindUtil util;
        util = new ViewPropertyBindUtil(context,data.getClass());
        return util;
    }

}
