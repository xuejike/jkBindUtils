package com.jkBindUtils.demo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jkBindUtils.demo.R;

/**
 * 通过ID 注解
 */
public class BookItemView extends LinearLayout {

    
    private ImageView ivImg;
    private TextView tvTitle;

    public BookItemView(Context context) {
        super(context);
        init();
    }

    public BookItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        View.inflate(getContext(), R.layout.book_item,this);
        ivImg = (ImageView) findViewById(R.id.book_img);
        tvTitle = (TextView) findViewById(R.id.book_name);
    }

    public String getTitle(){
        return tvTitle.getText().toString();
    }
    public void setTitle(String title){
        tvTitle.setText(title);
    }
    public void setImg(String img){

//        ivImg.setImageDrawable(img);
    }
    public Drawable getImg(){
        return ivImg.getDrawable();
    }
}
