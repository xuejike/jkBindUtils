package com.jkDataBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkDataBindUtils.annotation.BindViewId;
import com.jkDataBindUtils.annotation.BindViewProperty;
import com.jkDataBindUtils.demo.R;

/**
 * Created by xuejike on 2014/12/20.
 */
public class Book {
    @BindViewId(R.id.book_item_title)
    private String title;

    @BindViewId(R.id.book_item_img)
    @BindViewProperty("img")
    private Drawable image;

    public Book(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
