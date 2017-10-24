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

/**
 * 案例适配器
 * */
public class CaseAdapter extends BaseAdapter {
  private static final String TAG = "CaseAdapter";

  private Context mContext;

  private List<Baike> list;

  public CaseAdapter(Context mContext, List<Baike> list) {
    this.mContext = mContext;
    this.list = list;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int arg0) {
    return list.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return arg0;
  }

  @Override
  public View getView(int arg0, View convertView, ViewGroup arg2) {
    Views views = null;
    if (null == convertView) {
      views = new Views();
      convertView = LayoutInflater.from(mContext).inflate(R.layout.item_case, null);
      views.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
      views.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
      views.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
      views.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
      convertView.setTag(views);
    } else {
      views = (Views) convertView.getTag();
    }

    views.tv_info.setText(list.get(arg0).getTitle());
    views.iv_image.setBackgroundResource(list.get(arg0).getImg_url());
    
    return convertView;
  }

  private class Views {
    TextView tv_name;
    TextView tv_info;
    TextView tv_price;
    ImageView iv_image;
  }
}
