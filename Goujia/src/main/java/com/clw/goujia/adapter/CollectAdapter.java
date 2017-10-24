package com.clw.goujia.adapter;

import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.bean.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollectAdapter extends BaseAdapter {
  private static final String TAG = "CollectAdapter";

  private Context mContext;

  private List<Product> products;

  public CollectAdapter(Context mContext, List<Product> products) {
    this.mContext = mContext;
    this.products = products;
  }

  @Override
  public int getCount() {
    return products.size();
  }

  @Override
  public Object getItem(int arg0) {
    return products.get(arg0);
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
      convertView = LayoutInflater.from(mContext).inflate(R.layout.item_collect, null);
      views.tv_area = (TextView) convertView.findViewById(R.id.tv_area);
      views.tv_huxing = (TextView) convertView.findViewById(R.id.tv_huxing);
      views.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
      views.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
      views.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
      convertView.setTag(views);
    } else {
      views = (Views) convertView.getTag();
    }
    return convertView;
  }

  private class Views {
    TextView tv_name;
    ImageView iv_image;
    TextView tv_area;
    TextView tv_huxing;
    TextView tv_price;

  }
}
