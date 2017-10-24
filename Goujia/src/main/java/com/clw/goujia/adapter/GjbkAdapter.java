package com.clw.goujia.adapter;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import com.clw.goujia.App;
import com.clw.goujia.R;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 构家爆款适配器
 * */
public class GjbkAdapter extends BaseAdapter {
  private static final String TAG = "GjbkAdapter";
  
  private Context mContext;
  
  private List<String> list;
  
  public GjbkAdapter(Context mContext,List<String> list){
    this.mContext=mContext;
    this.list=list;
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
    Views views=null;
    if (convertView==null) {
      views=new Views();
      convertView=LayoutInflater.from(mContext).inflate(R.layout.item_gjbk, null);
      views.tv_info=(TextView) convertView.findViewById(R.id.tv_info);
      views.tv_price=(TextView) convertView.findViewById(R.id.tv_price);
      views.iv_image=(ImageView) convertView.findViewById(R.id.iv_image);
      views.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
      convertView.setTag(views);
    }else{
      views=(Views) convertView.getTag();
      
    }
    
    App.LoadImage("http://source.cloudin9.com/xintd/img/cover.jpg?ImageView2/2/w/1280", views.iv_image);
    return convertView;
  }
  
  private class Views{
    TextView tv_price;
    TextView tv_info;
    ImageView iv_image;
    TextView tv_name;
  }
}
