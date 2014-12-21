package com.jkDataBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jkDataBindUtils.bindUtils.ViewPropertyBindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ViewPropertyBindAdapter extends BaseAdapter {

    protected Context context;
    protected Class<? extends View> viewClass =null;
    protected List list;
    protected ViewPropertyBindUtil viewPropertyBindUtil;

    public ViewPropertyBindAdapter(Context context, Class<? extends View> viewClass, List list) {
        this.list = list;
        this.context = context;
        this.viewClass = viewClass;
    }

    public ViewPropertyBindAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
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
        if (viewPropertyBindUtil == null){
            if (viewClass == null){
                viewPropertyBindUtil = new ViewPropertyBindUtil(context,data.getClass());
            }else{
                viewPropertyBindUtil =new ViewPropertyBindUtil(context,viewClass,data.getClass());
            }
        }
        if (convertView == null){
            return viewPropertyBindUtil.createView(data);
        }else{
            return viewPropertyBindUtil.bind2View(convertView, data);
        }
    }
}
