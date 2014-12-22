package com.jkBindUtils.demo.vo;

import android.widget.Adapter;
import com.jkBindUtils.annotation.BindDisregard;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.annotation.BindViewId;
import com.jkBindUtils.demo.R;

/**
 * Created by xuejike on 2014/12/22.
 */
@BindView(layout = R.layout.main_item)
public class MainItem {
    
    @BindViewId(R.id.main_item_title)
    private String title;
    

    private Class activityClass;

    public MainItem(String title, Class activityClass) {
        this.title = title;
        this.activityClass = activityClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}
