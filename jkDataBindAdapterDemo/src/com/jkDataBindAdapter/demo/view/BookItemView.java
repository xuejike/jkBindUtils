package com.jkDataBindAdapter.demo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jkDataBindAdapter.demo.R;

/**
 * Created by xuejike on 2014/12/20.
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
        ivImg = (ImageView) findViewById(R.id.book_item_img);
        tvTitle = (TextView) findViewById(R.id.book_item_title);
    }

    public String getTitle(){
        return tvTitle.getText().toString();
    }
    public void setTitle(String title){
        tvTitle.setText(title);
    }
    public void setImg(Drawable img){
        ivImg.setImageDrawable(img);
    }
    public Drawable getImg(){
        return ivImg.getDrawable();
    }
}
