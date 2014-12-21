package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.jkBindUtils.bindAdapter.ViewPropertyBindAdapter;
import com.jkBindUtils.core.BindUtilConfig;
import com.jkBindUtils.core.DebugLevel;

import com.jkBindUtils.demo.vo.DemoItem;

import java.util.ArrayList;
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
        BindUtilConfig.debugLevel= DebugLevel.noInfo;
        ViewPropertyBindAdapter adapter=new ViewPropertyBindAdapter(this, TextView.class,list);
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
        list.add(new DemoItem(IdBindAdapterActivity.class,"视图ID数据绑定适配器"));
        list.add(new DemoItem(MPBAdpActivity.class,"多视图属性数据绑定适配器"));
    }
}
