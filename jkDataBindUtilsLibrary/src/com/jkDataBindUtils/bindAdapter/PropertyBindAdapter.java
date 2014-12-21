package com.jkDataBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jkDataBindUtils.bindUtils.PropertyBindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class PropertyBindAdapter extends BaseAdapter {

    protected Context context;
    protected Class<? extends View> viewClass;
    protected List list;
    protected PropertyBindUtil propertyBindUtil;

    public PropertyBindAdapter(Context context, Class<? extends View> viewClass, List list) {
        this.list = list;
        this.context = context;
        this.viewClass = viewClass;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object data = list.get(position);
        if (propertyBindUtil == null){
            propertyBindUtil =new PropertyBindUtil(context,viewClass,data.getClass());
        }
        if (convertView == null){
            return propertyBindUtil.createView(data);
        }else{
            return propertyBindUtil.loadData(convertView,data);
        }
    }
}
