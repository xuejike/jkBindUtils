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
public abstract class ViewBindAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected Context context;
    protected OnGetViewListener onGetViewListener;
    protected OnCreateViewListener onCreateViewListener;

    public ViewBindAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (onGetViewListener !=null){
            onGetViewListener.onGetViewBegin(position, convertView, parent);
        }
        T data = list.get(position);
        BindUtil util = getDataBindUtil(data);
        if (convertView == null){
            convertView = util.createView(list.get(position));
            if (onCreateViewListener !=null){
                onCreateViewListener.createView(convertView);
            }
            
        }else{
            util.bind2View(convertView, data);
        }
        if (onGetViewListener !=null){
            onGetViewListener.onGetViewFinish(position, convertView, data, parent);
        }
        return convertView;
    }

    public void setOnGetViewListener(OnGetViewListener onGetViewListener) {
        this.onGetViewListener = onGetViewListener;
    }

    public void setOnCreateViewListener(OnCreateViewListener onCreateViewListener) {
        this.onCreateViewListener = onCreateViewListener;
    }

    protected abstract BindUtil getDataBindUtil(T data);

    protected abstract BindUtil newDataBindUtilInstance(T data);
    
    
    public static interface OnGetViewListener {
        public void onGetViewBegin(int position, View convertView, ViewGroup parent);
        public void onGetViewFinish(int position, View returnView, Object data, ViewGroup parent);
    }
    public static interface OnCreateViewListener{
        public void createView(View newView);
        
    }
}
