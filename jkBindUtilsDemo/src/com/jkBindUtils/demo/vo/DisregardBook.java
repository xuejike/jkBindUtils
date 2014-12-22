package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindDisregard;
import com.jkBindUtils.annotation.BindViewProperty;

/**
 * Created by xuejike on 2014/12/22.
 */
public class DisregardBook {
    
    //使用 @BindDisregard 注解可以把不进行绑定的属性忽略掉
    @BindDisregard
    private String title;

    //当属性名与view的属性不一致时，可以使用@BindViewProperty(view属性) 进行绑定
    @BindViewProperty("img")
    private Drawable image;


    public DisregardBook(String title, Drawable image) {
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
