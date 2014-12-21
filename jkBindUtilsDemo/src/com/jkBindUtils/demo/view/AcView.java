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
public class AcView extends LinearLayout {

    private ImageView imageView;
    private Button btn;

    
    
    public AcView(Context context) {
        super(context);
        init();
    }

    public AcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AcView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void  init(){
        View.inflate(getContext(), R.layout.ac,this);
        imageView = (ImageView) findViewById(R.id.ac_iv);
        btn = (Button) findViewById(R.id.ac_btn);
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
