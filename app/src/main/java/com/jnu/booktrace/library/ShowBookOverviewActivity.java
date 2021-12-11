package com.jnu.booktrace.library;

import static com.jnu.booktrace.utils.ISBNApiUtil.*;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.utils.ISBNApiUtil;

import java.util.ArrayList;
import java.util.List;

public class ShowBookOverviewActivity extends AppCompatActivity {
    private List<Book> mBookList;
    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_overview);

        try {
            initData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initRecyclerView();
    }

    private void initData() throws InterruptedException {
        mBookList = new ArrayList<>();
        ISBNApiUtil isbnApiUtil = new ISBNApiUtil();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        isbnApiUtil.getBookFromISBN(book1,"9787020024759");
        isbnApiUtil.getBookFromISBN(book2,"9787040195835");
        isbnApiUtil.getBookFromISBN(book3,"9787505352377");
        Thread.sleep(500);//睡0.6秒保证数据加载完毕（草
        mBookList.add(book1);
        mBookList.add(book2);
        mBookList.add(book3);
        Log.e("MYTAG","展示界面的book "+book2.getAuthor());

    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.rv_book_overview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBookAdapter = new BookAdapter(mBookList);
        mRecyclerView.setAdapter(mBookAdapter);
    }

    public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Book> adpList;

        public BookAdapter(List<Book> adpList){
            this.adpList = adpList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book_overview_layout, parent, false);
            return new BookHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            BookHolder bookHolder = (BookHolder) holder;
            Book book = adpList.get(position);
            ISBNApiUtil isbnApiUtil = new ISBNApiUtil();
            isbnApiUtil.loadImageFromURL(book);
            try {
                Thread.sleep(500); //过于傻逼但有用
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("MYTAG","bitmap："+book.getImage_bitmap());
            bookHolder.iv_image.setImageBitmap(book.getImage_bitmap());
            //TODO：只能获取图片链接，要将链接转为可用图片资源
            bookHolder.tv_title.setText(book.getTitle());
            bookHolder.tv_author.setText(book.getAuthor());
        }

        @Override
        public int getItemCount() {
            return adpList.size();
        }

        class BookHolder extends RecyclerView.ViewHolder {
            private ImageView iv_image;
            private TextView tv_title;
            private TextView tv_author;
            public BookHolder(View itemView) {
                super(itemView);
                iv_image = itemView.findViewById(R.id.iv_image);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_author = itemView.findViewById(R.id.tv_author);
            }
        }
    }
}