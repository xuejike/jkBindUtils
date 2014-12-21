package com.jkBindUtils.demo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jkBindUtils.demo.R;

/**
 * Created by xuejike on 2014/12/21.
 */
public class AdView extends LinearLayout {

    private ImageView imageView;
    private Button btn;

    public AdView(Context context) {
        super(context);
        init();
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void  init(){
        View.inflate(getContext(), R.layout.ad, this);
//        imageView = (ImageView) findViewById(R.id.ad_iv);
//        btn = (Button) findViewById(R.id.ad_btn);
    }

    public String getTitle() {
        return btn.getText().toString();
    }

    public void setTitle(String title) {
        btn.setText(title);
    }

    public Drawable getImg() {
        return imageView.getDrawable();
    }

    public void setImg(Drawable img) {
        imageView.setImageDrawable(img);
    }
}
