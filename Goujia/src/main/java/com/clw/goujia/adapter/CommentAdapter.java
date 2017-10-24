package com.clw.goujia.adapter;

import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.bean.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
  private static final String TAG = "CommentAdapter";

  private Context mContext;

  private List<Comment> comments;

  public CommentAdapter(Context mContext, List<Comment> comments) {
    this.mContext = mContext;
    this.comments = comments;
  }

  @Override
  public int getCount() {
    return comments.size();
  }

  @Override
  public Object getItem(int arg0) {
    return comments.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return arg0;
  }

  @Override
  public View getView(int arg0, View convertView, ViewGroup arg2) {
    Views views;
    if (null == convertView) {
      views = new Views();
      convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comment, null);
      views.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
      views.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
      views.tv_reply = (TextView) convertView.findViewById(R.id.tv_reply);
      views.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
      views.iv_com = (ImageView) convertView.findViewById(R.id.iv_com_img);
      views.iv_headimg = (ImageView) convertView.findViewById(R.id.iv_headImg);
      convertView.setTag(views);
    } else {
      views = (Views) convertView.getTag();
    }

    views.tv_content.setText(comments.get(arg0).getContent());
    views.tv_date.setText(comments.get(arg0).getDate());
    views.tv_name.setText(comments.get(arg0).getName());

    
    views.tv_reply.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
      }
    });
    return convertView;
  }

  private class Views {
    TextView tv_name;
    TextView tv_content;
    ImageView iv_headimg;
    TextView tv_reply;
    TextView tv_date;
    ImageView iv_com;
  }
}
