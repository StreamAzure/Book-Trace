package com.jnu.booktrace.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Drift;
import com.jnu.booktrace.drift.DriftInfoActivity;
import com.jnu.booktrace.fragments.DriftFragment;

import java.util.List;

public class DriftAdapter extends RecyclerView.Adapter {

    private List<Drift> drifts;
    private Context context;

    public DriftAdapter(Context context,List<Drift> drifts) {
        this.context =context;
        this.drifts = drifts;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drift,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.getDrift_author().setText(drifts.get(position).getBook_Author());
        viewHolder.getDrift_title().setText(drifts.get(position).getTitle());
            viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DriftInfoActivity.class);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return drifts.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView drift_author, drift_title;
        private View rootView;

        public MyViewHolder(View view) {
            super(view);
            setDrift_author(view.findViewById(R.id.drift_author));
            setDrift_title(view.findViewById(R.id.drift_title));
            setRootView(view.findViewById(R.id.root_item_drift));
        }

        public TextView getDrift_author() {
            return drift_author;
        }

        public void setDrift_author(TextView drift_author) {
            this.drift_author = drift_author;
        }

        public TextView getDrift_title() {
            return drift_title;
        }

        public void setDrift_title(TextView drift_title) {
            this.drift_title = drift_title;
        }

        public View getRootView() {
            return rootView;
        }

        public void setRootView(View rootView) {
            this.rootView = rootView;
        }
    }

}
