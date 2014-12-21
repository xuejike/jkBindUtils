package com.jkDataBindUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
public class ViewIdBindAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private List list;

    public ViewIdBindAdapter(Context context, int resource, List list) {
        this.context = context;
        this.resource = resource;
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
        return null;
    }



}
