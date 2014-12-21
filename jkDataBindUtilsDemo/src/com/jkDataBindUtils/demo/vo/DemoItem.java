package com.jkDataBindUtils.demo.vo;

import com.jkDataBindUtils.annotation.BindDisregard;

/**
 * Created by xuejike on 2014/12/21.
 */
public class DemoItem {
    
    private Object tag;

    private String text;

    

    public DemoItem(Object tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
