package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.jkBindUtils.bindAdapter.ViewMapListBindAdapter;
import com.jkBindUtils.core.BindUtilConfig;
import com.jkBindUtils.core.DebugLevel;
import com.jkBindUtils.demo.view.BookItemView;
import com.jkBindUtils.demo.vo.PBook;

import java.util.*;

/**
 * Created by xuejike on 2014/12/25.
 */
public class ViewMapListActivity extends Activity {
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
        BindUtilConfig.debugLevel= DebugLevel.info;
        listview.setAdapter(getAdapter());
    }

    private ListAdapter getAdapter() {
        List list=new ArrayList();
        for (int i=0;i<30;i++){
            Map<String,Object> map =new HashMap<String, Object>();
            map.put("title","标题一");
            map.put("img",drawables.get(i%drawables.size()));
            list.add(map);
        }
        return new ViewMapListBindAdapter(this, BookItemView.class,list);
    }
}