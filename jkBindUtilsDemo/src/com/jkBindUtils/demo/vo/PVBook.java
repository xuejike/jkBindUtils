package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.annotation.BindViewProperty;
import com.jkBindUtils.demo.view.BookItemView;

/**
 * 使用@BindView(viewClass = view的class) 直接将java bean与view进行绑定
 */
@BindView(viewClass = BookItemView.class)
public class PVBook {

    //当属性名与view的属性一致时，可以直接绑定，
    private String title;

    //当属性名与view的属性不一致时，可以使用@BindViewProperty(view属性) 进行绑定
    @BindViewProperty("img")
    private Drawable image;

    public PVBook() {
    }

    public PVBook(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public Drawable getImage() {
        return image;
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
