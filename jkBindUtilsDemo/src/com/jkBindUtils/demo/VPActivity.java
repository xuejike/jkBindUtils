package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkBindUtils.bindAdapter.ViewPropertyBindAdapter;
import com.jkBindUtils.demo.view.BookItemView;
import com.jkBindUtils.demo.vo.PBook;
import com.jkBindUtils.demo.vo.PVBook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuejike on 2014/12/22.
 */
public class VPActivity extends Activity {
    private ListView listview;
    private LinkedList<Drawable> drawables;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview = (ListView)findViewById(R.id.listView);

        Resources resources = getResources();
        drawables = new LinkedList<Drawable>();

        drawables.add(resources.getDrawable(R.drawable.b1));
        drawables.add(resources.getDrawable(R.drawable.b2));
        drawables.add(resources.getDrawable(R.drawable.b3));
        drawables.add(resources.getDrawable(R.drawable.b4));
        drawables.add(resources.getDrawable(R.drawable.b5));
        drawables.add(resources.getDrawable(R.drawable.b6));
        drawables.add(resources.getDrawable(R.drawable.b7));

        String title = getIntent().getStringExtra("title");
        setTitle(title);
        listview.setAdapter(getViewPropertyBindAdapter());
    }
    
    //    将View与javabean进行属性绑定，主要可以应用在 组合自定义控件
    private ViewPropertyBindAdapter getViewPropertyBindAdapter(){
        List list=new ArrayList();
        for (int i=0;i<30;i++){
            list.add(new PVBook("图书标题"+i,drawables.get(i%drawables.size())));
        }

        ViewPropertyBindAdapter adapter =new ViewPropertyBindAdapter(this,BookItemView.class, list);
        return adapter;
    }
}