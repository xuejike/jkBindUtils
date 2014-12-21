package com.jkBindUtils.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import com.jkBindUtils.demo.vo.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuejike on 2014/12/21.
 */
public class IdBindUtilActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView = (ListView) findViewById(R.id.listView);
        
        List<Book> bookList = new LinkedList<Book>();

        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);

        for (int i = 0; i < 20 ;i ++){
            bookList.add(new Book("图书标题"+i,drawable));
        }

      //  IdBindUtil idBindUtil=new IdBindUtil(this,R.layout.book_item,)
    }
}