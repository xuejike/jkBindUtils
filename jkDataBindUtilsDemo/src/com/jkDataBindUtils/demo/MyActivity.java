package com.jkDataBindUtils.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.jkDataBindUtils.bindAdapter.PropertyBindAdapter;
import com.jkDataBindUtils.core.DataBindConfig;
import com.jkDataBindUtils.core.DebugLevel;
import com.jkDataBindUtils.demo.view.BookItemView;

import com.jkDataBindUtils.demo.vo.Book;
import com.jkDataBindUtils.demo.vo.DemoItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyActivity extends Activity {

    private ListView listview;
    private List<DemoItem> list;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview = (ListView)findViewById(R.id.listView);
        initData();
        DataBindConfig.debugLevel= DebugLevel.info;
        PropertyBindAdapter adapter=new PropertyBindAdapter(this, TextView.class,list);
        listview.setAdapter(adapter);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DemoItem demoItem = list.get(i);
                Class tag = (Class) demoItem.getTag();
                Intent intent=new Intent(MyActivity.this,tag);
                startActivity(intent);
            }
        });
        
    }
    
    private void  initData(){
        list = new ArrayList<DemoItem>();
        list.add(new DemoItem(PropertyBindUtilActivity.class,"视图属性数据绑定"));
        list.add(new DemoItem(PropertyBindAdapterActivity.class,"视图属性数据绑定适配器"));

    }
}
