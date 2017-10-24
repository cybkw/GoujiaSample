package com.clw.goujia.adapter;

import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.bean.Baike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShejiAdapter extends BaseAdapter {
  private static final String TAG = "BaikeAdapter";

  private Context mContext;

  private List<Baike> baikes;
  private int image;

  public ShejiAdapter(Context mContext, List<Baike> baikes,int image) {
    this.mContext = mContext;
    this.baikes = baikes;
    this.image=image;
  }

  @Override
  public int getCount() {
    return baikes.size();
  }

  @Override
  public Object getItem(int arg0) {
    return baikes.get(arg0);
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
      convertView = LayoutInflater.from(mContext).inflate(R.layout.item_baike, null);
      views.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
      views.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
      views.iv_big = (ImageView) convertView.findViewById(R.id.iv_big);
      convertView.setTag(views);
    } else {
      views = (Views) convertView.getTag();
    }

    views.tv_info.setText(baikes.get(arg0).getInfo());
    views.tv_title.setText(baikes.get(arg0).getTitle());
    views.iv_big.setBackgroundResource(image);
    return convertView;
  }

  private class Views {
    TextView tv_title;
    TextView tv_info;
    ImageView iv_big;
  }
}
