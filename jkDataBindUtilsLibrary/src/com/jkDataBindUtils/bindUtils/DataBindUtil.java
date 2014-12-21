package com.jkDataBindUtils.bindUtils;

import android.content.Context;
import android.view.View;

/**
 * Created by xuejike on 2014/12/21.
 */
public abstract class DataBindUtil<T> {
    

    protected Context context;
    protected Class<T> dataClass = null;
    private boolean initState=false;

    protected DataBindUtil(Context context) {
        this.context = context;
    }

    protected DataBindUtil(Context context, Class<T> dataClass) {
        this.context = context;
        this.dataClass = dataClass;
    }

    private void init(){
        if (!initState){
            readAnnotationView();
            buildMap();
            initState = true;
        }
    }

    public View createView(T data) {
        View v = createView();
        bind2View(v, data);
        return v;
    }
    public  View createView(){
        init();
        return getViewInstance();
    }
    public View bind2View(View view, T data){
        init();
        fillData2View( data,view);
        return view;
    }

    
    protected abstract void readAnnotationView();
    protected abstract View getViewInstance();

    protected abstract void  buildMap();

    /**
     * 把Data里的数据填充到View中
     * @param view
     * @param data
     */
    protected abstract void fillData2View(T data,View view);


    
    
}
