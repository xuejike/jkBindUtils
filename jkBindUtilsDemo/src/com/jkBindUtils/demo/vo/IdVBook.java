package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.annotation.BindViewId;
import com.jkBindUtils.demo.R;

/**
 * 使用@BindView(layout = 布局文件) 可以把整个javabean 绑定到对应的布局文件
 */
@BindView(layout = R.layout.book_item)
public class IdVBook {

    //使用@BindViewId(控件的Id) 可以将 属性 绑定到对应的 控件上
    @BindViewId(R.id.book_name)
    private String title;


    @BindViewId(R.id.book_img)
    private Drawable image;


    public IdVBook() {
    }

    public IdVBook(String title, Drawable image) {
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
