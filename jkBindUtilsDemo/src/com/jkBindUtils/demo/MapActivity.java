package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkBindUtils.bindAdapter.ViewPropertyBindAdapter;
import com.jkBindUtils.bindAdapter.ViewPropertyMapBindAdapter;
import com.jkBindUtils.demo.view.BookItemView;
import com.jkBindUtils.demo.vo.PBook;

import java.util.*;

/**
 * Created by xuejike on 2014/12/22.
 */
public class MapActivity extends Activity {
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
        listview.setAdapter(getViewPropertyMapBindAdapter());
    }

    //    通过在javabean上注解 要绑定的view对象，自动绑定
    private ViewPropertyMapBindAdapter getViewPropertyMapBindAdapter(){
        List list=new ArrayList();
        for (int i=0;i<30;i++){
            list.add(new PBook("图书标题"+i,drawables.get(i%drawables.size())));
        }
        Map<String,String> map=new HashMap<String, String>();
        map.put("title","title");
        map.put("image","img");
        ViewPropertyMapBindAdapter adapter =
                new ViewPropertyMapBindAdapter(this,BookItemView.class,map,list);
        return adapter;
    }
}