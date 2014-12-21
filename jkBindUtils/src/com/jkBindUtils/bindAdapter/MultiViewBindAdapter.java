package com.jkBindUtils.bindAdapter;

import android.content.Context;
import com.jkBindUtils.bindUtils.BindUtil;
import com.jkBindUtils.exception.ViewTypeNumOutOfMaxNum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2014/12/21.
 */
public abstract class MultiViewBindAdapter extends ViewBindAdapter {
    protected int viewTypeMaxSize;
    protected List<Class> viewClassList=new LinkedList<Class>();
    //class 是list中数据的Class 类
    protected Map<Class,BindUtil> viewUtilMap =new HashMap<Class, BindUtil>();

    public MultiViewBindAdapter(Context context, int maxViewTypeNum, List list) {
        super(context, list);
        viewTypeMaxSize = maxViewTypeNum;
    }

    @Override
    public int getItemViewType(int position) {
        return viewClassList.indexOf(list.get(position).getClass());
    }

    @Override
    public int getViewTypeCount() {
        return viewTypeMaxSize;
    }

    @Override
    protected BindUtil getDataBindUtil(Object data) {
        BindUtil util = viewUtilMap.get(data.getClass());
        if (util == null){
            util = newDataBindUtilInstance(data);
            viewUtilMap.put(data.getClass(),util);
            if (viewClassList.size() < viewTypeMaxSize){
                viewClassList.add(data.getClass());
            }else{
                throw new ViewTypeNumOutOfMaxNum(viewTypeMaxSize,viewClassList.size()+1);
            }
        }
        return util;
    }
}
