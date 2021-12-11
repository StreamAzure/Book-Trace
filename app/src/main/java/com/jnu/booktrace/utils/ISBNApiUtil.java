package com.jnu.booktrace.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.jnu.booktrace.bean.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

public class ISBNApiUtil {
    public static final String apikey = "11528.203958641994657fa3c05b913943515e.e5acbaf7197841cfa45c121acd0350d5";
    private static final int WHAT_ISBN_RESULT_OK = 200;

    public Book getBookFromISBN(Book book, String ISBN){
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == WHAT_ISBN_RESULT_OK) {
                    String result = msg.getData().getString("data");
                    try {
                        if(parsonJson(result, book)){
                            Log.e("MYTAG",result);
                            Log.e("MYTAG",book.getTitle());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new Message();
                    String ISBNRequest = "https://api.jike.xyz/situ/book/isbn/"+ISBN+"?apikey="+apikey;

                    Log.e("MYTAG", ISBNRequest);
                    String resultJson = download(ISBNRequest);
                    Log.e("MYTAG", resultJson);

                    message.what= WHAT_ISBN_RESULT_OK;
                    Bundle bundle = new Bundle();
                    bundle.putString("data",resultJson);
                    message.setData(bundle);
                    handler.sendMessage(message);

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();//线程启动读取网络数据
        return book;
    }

    private static String download(String strUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            //返回Json字符串
            URL url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            //判断HTTP报文的状态码
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //输入流
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //加一层缓冲，就可以一行一行读
                String line = "";
                while (null != (line = bufferedReader.readLine())) {
                    stringBuffer.append(line);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(stringBuffer);
    }

    private boolean parsonJson(String strJson, Book book){
        //解析Json
        //ArrayList<ShopLocation> arrayList = new ArrayList<>();
        try {
            JSONObject result = new JSONObject(strJson);
            String status = result.getString("msg");
            //TODO：还没处理API“请求成功”以外的情况，只是单纯地返回false
            if(status.equals("请求成功")){
                JSONObject bookInfo = new JSONObject(result.getString("data"));
                book.setId(bookInfo.getString("id"));
                book.setTitle(bookInfo.getString("name"));
                book.setImage(bookInfo.getString("photoUrl"));
                book.setAuthor(bookInfo.getString("author"));//TODO:多个作者的情况？
                book.setTranslator(bookInfo.getString("translator"));//TODO:多个译者的情况？
                book.setPublisher(bookInfo.getString("publishing"));
                book.setPubdate(bookInfo.getString("published"));
                book.setPrice(bookInfo.getString("price"));
                book.setPages(bookInfo.getInt("pages"));
                book.setAuthor_intro(bookInfo.getString("authorIntro"));
                book.setSummary(bookInfo.getString("description"));
                book.setBinding(bookInfo.getString("designed"));

                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
