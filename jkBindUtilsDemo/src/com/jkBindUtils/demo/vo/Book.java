package com.jkBindUtils.demo.vo;

import android.graphics.drawable.Drawable;
import com.jkBindUtils.annotation.BindView;
import com.jkBindUtils.annotation.BindViewId;
import com.jkBindUtils.annotation.BindViewProperty;
import com.jkBindUtils.demo.R;
import com.jkBindUtils.demo.view.BookItemView;

/**
 * Created by xuejike on 2014/12/20.
 */
@BindView(viewClass = BookItemView.class,layout = R.layout.book_item)
public class Book extends Bk{


    @BindViewId(R.id.book_item_img)
    @BindViewProperty("img")
    private Drawable image;

    public Book(String title, Drawable image) {
        super(title);
        this.image = image;
    }


    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
