package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindViewId;
import com.jkBindUtils.annotation.BindViewProperty;
import com.jkBindUtils.demo.R;

/**
 * Created by xuejike on 2014/12/22.
 */
public class IdBook {
    
    //使用@BindViewId(view的Id) 可以将java bean绑定到对应的View上
    @BindViewId(R.id.book_name)
    private String title;

    
    @BindViewId(R.id.book_img)
    private Drawable image;


    public IdBook() {
    }

    public IdBook(String title, Drawable image) {
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
