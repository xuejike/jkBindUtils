package com.jkDataBindUtils.bindAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jkDataBindUtils.bindUtils.ViewIdBindUtil;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ViewIdBindAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List list;
    private ViewIdBindUtil viewIdBindUtil;


    public ViewIdBindAdapter(Context context, int layout, List list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public ViewIdBindAdapter(Context context, List list) {
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

        if (viewIdBindUtil == null){
            viewIdBindUtil = new ViewIdBindUtil(context,layout,data.getClass());
        }

        if (convertView == null){
            return viewIdBindUtil.createView(data);
        }else{
            return viewIdBindUtil.bind2View(convertView,data);
        }
    }



}
