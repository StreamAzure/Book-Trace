package com.jnu.booktrace.activity;

import static com.jnu.booktrace.database.DBManager.QueryBook;
import static com.jnu.booktrace.database.DBManager.isBookExist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.bean.TagInfo;
import com.jnu.booktrace.utils.AndroidBarUtils;
import com.jnu.booktrace.utils.ISBNApiUtil;
import com.jnu.booktrace.utils.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryBookOverviewActivity extends AppCompatActivity {
    private List<Book> mBookList;
    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_book_overview);

        //状态栏颜色更改
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        AndroidBarUtils.setBarDarkMode(this,true); //状态栏文字图标颜色为黑色

        initToolbar();
        initData();
        initRecyclerView();
    }

    private void initToolbar(){
        //隐藏默认actionbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //获取toolbar
        toolbar = findViewById(R.id.toolbar);
//        //主标题，必须在setSupportActionBar之前设置，否则无效，如果放在其他位置，则直接setTitle即可
        toolbar.setTitle("");
        //用toolbar替换actionbar
        setSupportActionBar(toolbar);
        //左侧按钮：可见+更换图标+点击监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示toolbar的返回按钮
        //toolBar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        //预先加载三本书，方便调试
        String[] isbn={"9787020024759","9787505352377","9787040195835","9787544210966","9787544211765"};
        mBookList = new ArrayList<>();
        for(int i = 0; i < isbn.length; i++){
            Book book = new Book();
            if(!isBookExist(isbn[i])){ //如果数据库中没有则请求，请求完了加入到（本地）数据库
                new ISBNApiUtil().getBookFromISBN(book, isbn[i]);
            }
            mBookList.add(QueryBook(isbn[i]));
        }
    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.rv_book_overview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBookAdapter = new BookAdapter(this, mBookList);
        mRecyclerView.setAdapter(mBookAdapter);
    }

    public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Book> adpList;
        private Context context;
        HashMap<String, Drawable> imgCache;     // 图片缓存
        HashMap<Integer, TagInfo> tag_map;      // TagInfo缓存
        ImageUtil loader;                // 异步加载图片类


        public BookAdapter(Context context, List<Book> bookList){
            this.context = context;
            this.adpList = bookList;
            imgCache = new HashMap<String, Drawable>();
            loader = new ImageUtil();
            tag_map = new HashMap<Integer, TagInfo>();

        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_book_overview_layout,parent,false);
            return new BookHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            BookHolder bookHolder = (BookHolder) holder;
            Book book = adpList.get(position);
            bookHolder.tvTitle.setText(book.getTitle());
            bookHolder.tvAuthor.setText(book.getAuthor());
            bookHolder.tvAbstract.setText(book.getSummary());
            bookHolder.tvBookShelf.setText("");//TODO:确定书籍所属书架的名字

            String imgurl = adpList.get(position).getImage();   // 得到该项所代表的url地址
            Drawable drawable = imgCache.get(imgurl);       // 先去缓存中找

            TagInfo tag = new TagInfo();
            tag.url = adpList.get(position).getImage();

            if (null != drawable) {                         // 找到了直接设置为图像
                bookHolder.ivImage.setImageDrawable(drawable);
            }
            else {                                      // 没找到则开启异步线程
                drawable = loader.loadDrawableByTag(tag, new ImageUtil.ImageCallBack() {
                    @Override
                    public void obtainImage(TagInfo ret_info) {
                        imgCache.put(ret_info.url, ret_info.drawable);    // 首先把获取的图片放入到缓存中
                        // 通过返回的TagInfo去Tag缓存中找，然后再通过找到的Tag来获取到所对应的ImageView
                        bookHolder.ivImage.setImageDrawable(ret_info.drawable);
                    }
                });
            }

            /**
             * 书籍项的点击事件，跳转至详情页
             */
            bookHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LibraryBookDetailActivity.class);
                    intent.putExtra("book",(Parcelable)book);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return adpList.size();
        }

        class BookHolder extends RecyclerView.ViewHolder{
            View rootView;
            ImageView ivImage;
            TextView tvTitle,tvAuthor,tvAbstract,tvBookShelf;
            public BookHolder(@NonNull View itemView) {
                super(itemView);
                rootView = itemView.findViewById(R.id.item_book_root_view);
                ivImage = itemView.findViewById(R.id.iv_item_book_image);
                tvTitle = itemView.findViewById(R.id.tv_item_book_title);
                tvAuthor = itemView.findViewById(R.id.tv_item_book_author);
                tvAbstract = itemView.findViewById(R.id.tv_item_book_abstract);
                tvBookShelf = itemView.findViewById(R.id.tv_item_book_belong_bookshelf);
            }
        }
    }

}