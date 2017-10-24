package com.clw.goujia.adapter;

import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.bean.LinkMan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ChooseAdapter extends BaseAdapter {
  private static final String TAG = "ChooseAdapter";

  private Context mContext;

  private List<LinkMan> linkMans;

  public ChooseAdapter(Context mContext, List<LinkMan> linkMans) {
    this.mContext = mContext;
    this.linkMans = linkMans;
  }

  @Override
  public int getCount() {
    return linkMans.size();
  }

  @Override
  public Object getItem(int arg0) {
    return linkMans.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return arg0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup arg2) {
    Views views;
    if (null == convertView) {
      views = new Views();
      convertView = LayoutInflater.from(mContext).inflate(R.layout.item_store, null);
      views.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
      views.tv_linkman = (TextView) convertView.findViewById(R.id.tv_linkman);
      views.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
      views.tv_store = (TextView) convertView.findViewById(R.id.tv_storename);
      views.cb_check = (CheckBox) convertView.findViewById(R.id.cb_check);
      convertView.setTag(views);
    } else {
      views = (Views) convertView.getTag();
    }

    views.cb_check.setChecked(linkMans.get(position).isCheck());
    return convertView;
  }

  private class Views {
    TextView tv_store;
    TextView tv_linkman;
    TextView tv_phone;
    TextView tv_address;
    CheckBox cb_check;
  }
}
