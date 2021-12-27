package com.jnu.booktrace.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.adapter.PostAdapter;
import com.jnu.booktrace.adapter.ReplyAdapter;
import com.jnu.booktrace.bean.Reply;
import com.jnu.booktrace.utils.AndroidBarUtils;

import java.util.ArrayList;
import java.util.List;

public class FreeTalkPostActivity extends AppCompatActivity {
    private List<Reply> replyList;
    private int mCurrentPosition = 0;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReplyAdapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_talk_post);

        //状态栏颜色更改
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AndroidBarUtils.setBarDarkMode(this,true); //状态栏文字图标颜色为黑色

        initToolbar();
        initData();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        adapter = new ReplyAdapter(this, replyList);
        recyclerView.setAdapter(adapter);
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
        toolbar.setTitle("详情");
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

    private void initData(){
        replyList = new ArrayList<>();
        replyList.add(new Reply(R.drawable.touxiang,"少女漫步世界","社恐是借口\n" +
                "就是想要手里时时刻刻捧着书","2021-12-19"));
        replyList.add(new Reply(R.drawable.touxiang,"サボテン","《罪与罚》拉斯柯尔尼科夫。目前为止唯一和我没有交流障碍的人。","2021-12-18"));
        replyList.add(new Reply(R.drawable.touxiang,"花城出版社","你那么憎恨那些人，跟他们斗了那么久，最终却变得和他们一样。人世间没有任何理想值得以这样的沉沦为代价。","2021-12-18"));
        replyList.add(new Reply(R.drawable.touxiang,"Asuraa","一定是《飘》里的郝思嘉\uD83D\uDE0A当然不是那般美貌，而是即便世界坍塌，也有勇气和决心，白地重建的生命力。","2021-12-19"));
        replyList.add(new Reply(R.drawable.touxiang,"要下雨了","我惧怕自己不是美玉，因而刻意不去刻苦打磨；我又对自己会成为美玉尚存半分希望，因而也无法庸庸碌碌地与瓦砾为伍，我渐渐远离俗世，疏远世人，结果，愤懑、羞惭和愤恨渐渐滋长了我内心怯懦的自尊。","2021-12-16"));
        replyList.add(new Reply(R.drawable.touxiang,"！","我惧怕自己不是美玉，因而刻意不去刻苦打磨；我又对自己会成为美玉尚存半分希望，因而也无法庸庸碌碌地与瓦砾为伍，我渐渐远离俗世，疏远世人，结果，愤懑、羞惭和愤恨渐渐滋长了我内心怯懦的自尊。","2021-12-13"));
        replyList.add(new Reply(R.drawable.touxiang,"锋少","我惧怕自己不是美玉，因而刻意不去刻苦打磨；我又对自己会成为美玉尚存半分希望，因而也无法庸庸碌碌地与瓦砾为伍，我渐渐远离俗世，疏远世人，结果，愤懑、羞惭和愤恨渐渐滋长了我内心怯懦的自尊。","2021-12-22"));
        replyList.add(new Reply(R.drawable.touxiang,"V","我真的很想说是堂吉诃德，但是那么喜欢他的我，更像桑丘吧。","2021-12-22"));
        replyList.add(new Reply(R.drawable.touxiang,"紫皮大蒜","红楼梦的鸳鸯，她绞断发，以死与婚姻作抵抗。那个女儿无言的时代，她发毒誓，叩天地，宁愿日头月亮照着嗓子，从嗓子里头长疔烂了出来，烂化成酱，她也不愿结婚。二十一世纪，鸳鸯女仍在，红楼梦未完......","2021-12-13"));
        replyList.add(new Reply(R.drawable.touxiang,"南在南方","必须是《围城》里的方鸿渐，时而自卑，时而羞愧，时而优柔寡断，没有什么本事，也没有好运气，仅仅是活着。抓不住幸福，最后把生活过得一地鸡毛。","2021-12-26"));
    }

}