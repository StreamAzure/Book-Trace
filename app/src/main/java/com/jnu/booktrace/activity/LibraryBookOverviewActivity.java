package com.jnu.booktrace.activity;

import static com.jnu.booktrace.database.DBManager.QueryBook;
import static com.jnu.booktrace.database.DBManager.isBookExist;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.adapter.BookAdapter;
import com.jnu.booktrace.bean.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookOverviewActivity extends AppCompatActivity {
    private List<Book> mBookList;
    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_overview);

        initData();
        initRecyclerView();
    }

    private void initData() {
        mBookList = new ArrayList<>();
        if(isBookExist("9787020024759")) {
            Book book = QueryBook("9787020024759");
            mBookList.add(book);
        }
        if(isBookExist("9787040195835")){
            Book book = QueryBook("9787040195835");
            mBookList.add(book);
        }
        if(isBookExist("9787505352377")){
            Book book = QueryBook("9787505352377");
            mBookList.add(book);
        }
//        Log.e("bookExist", isBookExist("9787020024759")+" 9787020024759");
//        Log.e("bookExist", isBookExist("9787040195835")+" 9787040195835");
//        Log.e("bookExist", isBookExist("9787505352377")+" 9787505352377");
//        isbnApiUtil.getBookFromISBN(book1, "9787020024759");
//        isbnApiUtil.getBookFromISBN(book2,"9787040195835");
//        isbnApiUtil.getBookFromISBN(book3,"9787505352377");
//        try {
//            sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        insertBooktb(book1);
//        insertBooktb(book2);
//        insertBooktb(book3);
//        mBookList.add(book1);
//        mBookList.add(book2);
//        mBookList.add(book3);
    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.rv_book_overview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBookAdapter = new BookAdapter(this, mBookList);
        mRecyclerView.setAdapter(mBookAdapter);
    }
}