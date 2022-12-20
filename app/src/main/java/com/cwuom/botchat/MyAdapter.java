package com.cwuom.botchat;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.List;

/* 把数据组装到页面控件中，生成所有的行 */
public class MyAdapter extends BaseAdapter {

    // 数据bean
    private List<ChatList> chatList;
    private Context context;
    private int h;

    /**
     * 含参构造
     * @param chatList  商品集合
     * @param context   上下文对象
     * @param h 占位高度
     */
    public MyAdapter(List<ChatList> chatList, Context context, int h) {
        super();
        this.chatList = chatList;
        this.context = context;
        this.h = h;
    }

    /**
     * @return 获取总加载数量，返回总数据集合的大小
     */
    @Override
    public int getCount() {
        return chatList.size();
    }

    /**
     * @param position  获取具体行的控件item
     * @return  返回获取具体行的控件item
     */
    @Override
    public Object getItem(int position) {
        return chatList.get(position);
    }

    /**
     * @param position  当前位置的行号
     * @return 返回当前位置的行号
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_item,null);

        // 设置列表元素的信息
        ImageView img = convertView.findViewById(R.id.imageview);
        TextView tv_title = convertView.findViewById(R.id.textview);
        TextView tv_sub = convertView.findViewById(R.id.textview_sub);
        LinearLayout ll;
        ll = convertView.findViewById(R.id.ll);


        ChatList chat = chatList.get(position);
        Integer imgId = chat.getImgId();
        String title = chat.getTitle();
        String sub = chat.getSub();
        Boolean iTB = chat.getiTB();
        CardView cardView = convertView.findViewById(R.id.cv_img);

        img.setImageResource(imgId);
        tv_title.setText(title);
        tv_sub.setText(sub);


        if (iTB){ // 如果这个VIEW只是占位作用
            ll.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) cardView.getLayoutParams();
            params.height = h;
            cardView.setLayoutParams(params);
        }

        // 点击事件监听
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll.getVisibility() == View.VISIBLE){
                    Toast.makeText(context,"点击的是第"+(position)+"行",Toast.LENGTH_LONG).show();
                }
            }
        });
        return convertView;
    }
}