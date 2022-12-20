package com.cwuom.botchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * bilibili@im-cwuom
 * >记得关注我<
 * 跑路交流群:893018099
 * GITHUB: cwuom
 * WeChat: cwuomcwuom00
 * */

/**
 * 本人并不是专业的开发人员，技术有限，大佬们看看就行
 * 代码为经过优化处理，部分细节问题请自行补充CODE，仅供参考与学习！
 * */

public class MainActivity extends AppCompatActivity {

    // 名称表
    private String[] data = {"小明", "小红", "小花", "小力", "老六",
            "翠花","小明", "小红", "小花", "小力", "老六",
            "翠花","小明", "小红", "小花", "小力", "老六",
            "翠花","小明", "小红", "小花", "小力", "老六",
            "翠花","翠花","小明", "小红", "小花", "小力", "老六",
            "翠花","翠花","小明", "小红", "小花", "小力", "老六",
            "翠花","翠花","小明", "小红", "小花", "小力", "老六",
            "翠花"};

    private ListView mListView; // 你懂的，滑动列表

    private List<ChatList> chatList; // 用于自定义列表中展示信息


    private CardView mCvTabBlur; // 下方的TAB（模糊）
    private CardView mCvTabBlur_2; // 上方的TAB（模糊）

    private int h; // 定义TAB高度，用来自适应控件高度的变化以及不同机型下的高度不同
    private final MyHandler mHandler = new MyHandler(this);

    private ListView listView;


    /* 图片ID数组 */
    private int[] mImageId = new int[] {R.drawable.a, R.drawable.aa, R.drawable.aaaa, R.drawable.aaa, R.drawable.aaaaaa,
            R.drawable.aaa, R.drawable.a, R.drawable.aaaaaaa, R.drawable.aaaaaa, R.drawable.aaaa,R.drawable.a, R.drawable.aa, R.drawable.aaaa, R.drawable.aaa, R.drawable.aaaaaa,
            R.drawable.aaa, R.drawable.a, R.drawable.aaaaaaa, R.drawable.aaaaaa, R.drawable.aaaa,R.drawable.a, R.drawable.aa, R.drawable.aaaa, R.drawable.aaa, R.drawable.aaaaaa,
            R.drawable.aaa, R.drawable.a, R.drawable.aaaaaaa, R.drawable.aaaaaa, R.drawable.aaaa,R.drawable.a, R.drawable.aa, R.drawable.aaaa, R.drawable.aaa, R.drawable.aaaaaa,
            R.drawable.aaa, R.drawable.a, R.drawable.aaaaaaa, R.drawable.aaaaaa, R.drawable.aaaa, };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data
        );

        /*
        * findViewById 没啥可讲
        * */
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        mCvTabBlur = findViewById(R.id.cardview_blur);
        mCvTabBlur_2 = findViewById(R.id.cardview_blur_2);

        mCvTabBlur.setCardBackgroundColor(Color.TRANSPARENT);
        mCvTabBlur.setCardElevation(0);

        mCvTabBlur_2.setCardBackgroundColor(Color.TRANSPARENT);
        mCvTabBlur_2.setCardElevation(0);


        mListView = findViewById(R.id.list_view);



        /* 获取TAB高度
        * Q：为什么要先POST
        * =====================================================
        * A：在activity中可以调用View.getWidth、View.getHeight()、View.getMeasuredWidth() 、View.getgetMeasuredHeight()来获得某个view的宽度或高度，
        * 但是在onCreate()、onStrart()、onResume()方法中会返回0，
        * 这是应为当前activity所代表的界面还没显示出来没有添加到WindowPhone的DecorView上或要获取的view没有被添加到DecorView上或者该View的visibility属性为gone 或者该view的width或height真的为0。
        * 所以只有上述条件都不成立时才能得到非0的width和height。
        * =====================================================
        * 详情请参考: https://blog.csdn.net/limonzet/article/details/64444629
        * =====================================================
        *  */
        mCvTabBlur.post(new Runnable() {
            @Override
            public void run() {
                h = mCvTabBlur.getHeight();
                Log.e("h", String.valueOf(h));
                mHandler.sendEmptyMessage(10086); // 成功获取，更新LISTVIEW
            }
        });





    }
    private class MyHandler extends Handler {

        public MyHandler(MainActivity activity) {
            WeakReference<MainActivity> mTarget = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10086){
                // 将数据封装至模板
                chatList = new ArrayList<>();

                /* 第一个元素 设置ITB为TRUE*/
                ChatList Schat = new ChatList();
                Schat.setTitle(data[0]);
                Schat.setSub("");
                Schat.setImgId(mImageId[0]);
                Schat.setITB(true); // 把此VIEW变成占位符，可以使上条VIEW正常显示不被其TAB遮挡
                chatList.add(Schat);

                for (int i = 0; i < mImageId.length; i++) {
                    ChatList chat = new ChatList();
                    chat.setTitle(data[i]); // 设置聊天列表标题
                    chat.setSub("这是一个测试"); // 自定义消息文字
                    chat.setImgId(mImageId[i]); // 设置图片
                    chat.setITB(false); // 是否是第一个元素
                    chatList.add(chat);
                }

                /* 最后一个元素 设置ITB为TRUE*/
                ChatList Echat = new ChatList();
                Echat.setTitle(data[0]);
                Echat.setSub("");
                Echat.setImgId(mImageId[0]);
                Echat.setITB(true); // 把此VIEW变成占位符，可以使上条VIEW正常显示不被其TAB遮挡
                chatList.add(Echat);


                // 利用适配器进行组装
                MyAdapter myAdapter = new MyAdapter(chatList,MainActivity.this, h);
                // 接通视图
                listView.setAdapter(myAdapter);

            }
        }
    }

}