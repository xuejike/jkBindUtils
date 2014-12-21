package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.demo.view.AdView;

/**
 * Created by xuejike on 2014/12/21.
 */
@BindView(viewClass = AdView.class)
public class Ad {
    private String title;
    private Drawable img;

    public Ad(String title, Drawable img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
