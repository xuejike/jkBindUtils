package com.jkDataBindUtils.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkDataBindUtils.bindAdapter.ViewPropertyBindAdapter;
import com.jkDataBindUtils.demo.view.BookItemView;
import com.jkDataBindUtils.demo.vo.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuejike on 2014/12/21.
 */
public class PropertyBindAdapterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_bind_adapter);
        ListView listView = (ListView) findViewById(R.id.listView);

        // 生成数据
        List<Book> bookList = new LinkedList<Book>();

        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);

        for (int i = 0; i < 20 ;i ++){
            bookList.add(new Book("图书标题"+i,drawable));
        }

        ViewPropertyBindAdapter adapter = new ViewPropertyBindAdapter(this,bookList);

        listView.setAdapter(adapter);
    }
}