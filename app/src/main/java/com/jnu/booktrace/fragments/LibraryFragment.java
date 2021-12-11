package com.jnu.booktrace.fragments;

import static com.jnu.booktrace.utils.ISBNApiUtil.download;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.booktrace.R;
import com.jnu.booktrace.ScanActivity;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {
    private static final int WHAT_DATA_OK = 100;
    public static final String apikey = "11528.203958641994657fa3c05b913943515e.e5acbaf7197841cfa45c121acd0350d5";
    private Button button;
    private EditText editText;

    public LibraryFragment() {}
    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        button = rootView.findViewById(R.id.btn);
        editText = rootView.findViewById(R.id.et);
        button.setText("提交");

        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == WHAT_DATA_OK) {
                    String result = msg.getData().getString("data");
                    try {
                        if(result.length()>0){
                            Log.e("MYTAG",result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //通过网络获取坐标信息
                        try {
                            String url = editText.getText().toString();
                            url = "https://api.jike.xyz/situ/book/isbn/"+url+"?apikey="+apikey;
                            Log.e("MYTAG", url);
                            String str = download(url);
                            Log.e("MYTAG", str);
                            Message message = new Message();
                            message.what= WHAT_DATA_OK;
                            Bundle bundle = new Bundle();
                            bundle.putString("data",str);
                            message.setData(bundle);
                            handler.sendMessage(message);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(runnable).start();//线程启动读取网络数据
            }
        });



        return rootView;
    }


    public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}