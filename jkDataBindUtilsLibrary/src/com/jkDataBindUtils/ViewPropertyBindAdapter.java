package com.jkDataBindUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jkDataBindUtils.core.ReflectUtils;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ViewPropertyBindAdapter extends BaseAdapter {

    protected Context context;
    protected Class<? extends View> viewClass;
    protected List list;

    public ViewPropertyBindAdapter(Context context, Class<? extends View> itemView, List list) {
        this.context = context;
        this.viewClass = itemView;
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = ReflectUtils.createView(viewClass,context);
        }
        ReflectUtils.loadData(view,list.get(i));
        return view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
