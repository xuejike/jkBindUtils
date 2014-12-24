package com.jkBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jkBindUtils.bindUtils.BindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/21.
 */
public abstract class ViewBindAdapter extends BaseAdapter {
    protected List list;
    protected Context context;
    protected GetViewListener getViewListener;

    public ViewBindAdapter(Context context, List list) {
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
        if (getViewListener !=null){
            getViewListener.onGetViewBegin(position, convertView, parent);
        }
        Object data = list.get(position);
        BindUtil util = getDataBindUtil(data);
        if (convertView == null){
            convertView = util.createView(list.get(position));
        }else{
            util.bind2View(convertView, data);
        }
        if (getViewListener !=null){
            getViewListener.onGetViewFinish(position, convertView, data, parent);
        }
        return convertView;
    }

    public void setOnGetViewListener(GetViewListener getViewListener) {
        this.getViewListener = getViewListener;
    }

    protected abstract BindUtil getDataBindUtil(Object data);

    protected abstract BindUtil newDataBindUtilInstance(Object data);
    
    
    public static interface GetViewListener {
        public void onGetViewBegin(int position, View convertView, ViewGroup parent);
        public void onGetViewFinish(int position, View returnView, Object data, ViewGroup parent);
    }
}
