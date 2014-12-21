package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkBindUtils.bindAdapter.MultiViewPropertyBindAdapter;
import com.jkBindUtils.core.BindUtilConfig;
import com.jkBindUtils.core.DebugLevel;
import com.jkBindUtils.demo.vo.Ac;
import com.jkBindUtils.demo.vo.Ad;
import com.jkBindUtils.demo.vo.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuejike on 2014/12/21.
 */
public class MPBAdpActivity extends Activity {
    private ListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview = (ListView)findViewById(R.id.listView);
        Resources resources = getResources();
        BindUtilConfig.debugLevel = DebugLevel.debug;
        Drawable d1 = resources.getDrawable(R.drawable.general__shared__share_mark);
        Drawable d2 = resources.getDrawable(R.drawable.mibi_ic_milicenter);
        Drawable d3 = resources.getDrawable(R.drawable.reading__bitmap_factory_view__avatar);
        List list = new ArrayList();
        for (int i=0;i<10;i++){
            Ac ac=new Ac("acT"+i,d1);
            Ad ad =new Ad("ADD"+i,d2);
            
           // DemoItem demoItem =new DemoItem("xx","demo"+i);
           list.add(ac);
           list.add(ad);
         
           // list.add(demoItem);

        }
        for (int i =0;i<20;i++){
            Book book =new Book("book"+i,d3);
            list.add(book);
        }
        MultiViewPropertyBindAdapter adapter=new MultiViewPropertyBindAdapter(this,list, 3);
        listview.setAdapter(adapter);
    }
}