package com.jnu.booktrace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.bean.Bookshelf;

import java.util.List;

public class BookshelfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Bookshelf> adpList;
    private Context context;

    public BookshelfAdapter(Context context, List<Bookshelf> bookshelfList){
        this.context = context;
        this.adpList = bookshelfList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_book_overview_layout,parent,false);
        return new BookshelfHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookshelfHolder bookHolder = (BookshelfHolder) holder;
        Bookshelf bookshelf = adpList.get(position);

    }

    @Override
    public int getItemCount() {
        return adpList.size();
    }

    class BookshelfHolder extends RecyclerView.ViewHolder{

        public BookshelfHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
