package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkBindUtils.bindAdapter.MultiViewIdBindAdapter;
import com.jkBindUtils.bindAdapter.ViewPropertyBindAdapter;
import com.jkBindUtils.demo.view.BookItemView;
import com.jkBindUtils.demo.vo.IdVBook;
import com.jkBindUtils.demo.vo.MainItem;
import com.jkBindUtils.demo.vo.PBook;
import com.jkBindUtils.demo.vo.PVBook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuejike on 2014/12/22.
 */
public class MIActivity extends Activity {
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
        listview.setAdapter(getMultiViewIdBindAdapter());
    }
    


    //    多视图绑定，可以在一个listview中绑定多种视图(支持ID或属性绑定)
    private MultiViewIdBindAdapter getMultiViewIdBindAdapter(){
        List list =new ArrayList();
        for (int i=0;i<30;i++){
            list.add(new IdVBook("图书标题"+i,drawables.get(i%drawables.size())));
            if (i%2==0){
                list.add(new MainItem("第三者NO"+i,null));
            }
        }
        MultiViewIdBindAdapter adapter =new MultiViewIdBindAdapter(this,2,list);
//        MultiViewPropertyBindAdapter
        return adapter;
    }
}