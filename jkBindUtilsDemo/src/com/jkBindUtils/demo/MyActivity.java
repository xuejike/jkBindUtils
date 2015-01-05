package com.jkBindUtils.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.jkBindUtils.bindAdapter.ViewBindAdapter;
import com.jkBindUtils.bindAdapter.ViewIdBindAdapter;

import com.jkBindUtils.demo.vo.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyActivity extends Activity {

    private ListView listview;
    private List<MainItem> list;
    private List<Drawable> drawables;


    /**
     * Called when the activity is first created.
     */
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
        initData();
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainItem item = list.get(i);
                Intent intent=new Intent(MyActivity.this,item.getActivityClass());
                intent.putExtra("title",item.getTitle());
                startActivity(intent);
            }
        });
        
    }
    
    private void  initData(){
        list = new ArrayList<MainItem>();
        list.add(new MainItem("对javabean的字段与layout中的Id进行绑定绑定",IActivity.class));
        list.add(new MainItem("对JavaBean与layout绑定",VIActivity.class));
        list.add(new MainItem("对JavaBean的字段与view中的属性进行绑定",PActivity.class));
        list.add(new MainItem("对JavaBean与View进行绑定",VPActivity.class));
        list.add(new MainItem("对JavaBean的字段与View的属性进行映射绑定",MapActivity.class));
        list.add(new MainItem("对JavaBean的字段进行过滤绑定",DActivity.class));
        list.add(new MainItem("对Map 集合进行数据绑定",ViewMapListActivity.class));

        list.add(new MainItem("对多个不同的View进行绑定",MIActivity.class));
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);list.add(null);list.add(null);list.add(null);list.add(null);
        
        ViewIdBindAdapter adapter=new ViewIdBindAdapter(this,list);
        
        adapter.setOnGetViewListener(new ViewBindAdapter.OnGetViewListener() {
            @Override
            public void onGetViewBegin(int position, View convertView, ViewGroup parent) {
                System.out.println("Get...."+position);
            }

            @Override
            public void onGetViewFinish(int position, View returnView, Object data, ViewGroup parent) {
                System.out.println("Get-----"+position);
            }
        });
        listview.setAdapter(adapter);
    }
    
    





}
