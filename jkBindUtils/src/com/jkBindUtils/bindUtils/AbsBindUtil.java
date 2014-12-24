package com.jkBindUtils.bindUtils;

import android.content.Context;
import android.view.View;

/**
 * Created by xuejike on 2014/12/21.
 */
public abstract class AbsBindUtil<T> implements BindUtil<T> {
    

    protected Context context;
    protected Class<T> dataClass = null;
    private boolean initState=false;

    protected AbsBindUtil(Context context) {
        this.context = context;
    }

    protected AbsBindUtil(Context context, Class<T> dataClass) {
        this.context = context;
        this.dataClass = dataClass;
    }

    protected void init(){
        if (!initState){
            readAnnotationView();
            buildMap();
            initState = true;
        }
    }

    @Override
    public View createView(T data) {
        View v = createView();
        bind2View(v, data);
        return v;
    }
    @Override
    public  View createView(){
        init();

        View view = getViewInstance();
        return view;
    }
    @Override
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
