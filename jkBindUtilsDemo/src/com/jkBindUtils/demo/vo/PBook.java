package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.annotation.BindViewId;
import com.jkBindUtils.annotation.BindViewProperty;
import com.jkBindUtils.demo.R;
import com.jkBindUtils.demo.view.BookItemView;

/**
 * 通过属性注解 进行数据绑定，view和java bean 必须要有对应的Getter，Setter方法
 */

public class PBook {

    //当属性名与view的属性一致时，可以直接绑定，
    private String title;
    
    //当属性名与view的属性不一致时，可以使用@BindViewProperty(view属性) 进行绑定
    @BindViewProperty("img")
    private Drawable image;


    public PBook() {
    }

    public Drawable getImage() {
        return image;
    }
    public PBook(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
