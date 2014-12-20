package com.jkDataBindAdapter.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkDataBindAdapter.annotation.ViewProperty;

/**
 * Created by xuejike on 2014/12/20.
 */
public class Book {
    private String title;

    @ViewProperty("img")
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
