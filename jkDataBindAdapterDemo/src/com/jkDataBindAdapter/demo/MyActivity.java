package com.jkDataBindAdapter.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkDataBindAdapter.ViewPropertyBindAdapter;
import com.jkDataBindAdapter.demo.view.BookItemView;
import com.jkDataBindAdapter.demo.vo.Book;

import java.util.LinkedList;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = (ListView) findViewById(R.id.listView);

        // 生成数据
        List<Book> bookList = new LinkedList<Book>();

        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);

        for (int i = 0; i < 20 ;i ++){
            bookList.add(new Book("图书标题"+i,drawable));
        }

        ViewPropertyBindAdapter adapter = new ViewPropertyBindAdapter(this, BookItemView.class,bookList);

        listView.setAdapter(adapter);
    }
}
