package com.clw.goujia.adapter;

import com.clw.goujia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ComMenuAdapter extends BaseAdapter {
  private static final String TAG = "ComMenuAdapter";

  private String[] names;

  private int[] images;

  private Context mContext;

  public ComMenuAdapter(Context mContext, String[] names, int[] images) {
    this.names = names;
    this.images = images;
    this.mContext = mContext;

  }

  @Override
  public int getCount() {
    return images.length;
  }

  @Override
  public Object getItem(int arg0) {
    return images[arg0];
  }

  @Override
  public long getItemId(int arg0) {
    return 0;
  }

  @Override
  public View getView(int arg0, View convertView, ViewGroup arg2) {
//    if (condition) {
      convertView=LayoutInflater.from(mContext).inflate(R.layout.item_com_menu, null);
      TextView tv_name=(TextView) convertView.findViewById(R.id.tv_name);
      ImageView iv_image=(ImageView) convertView.findViewById(R.id.iv_image);
      
      iv_image.setImageResource(images[arg0]);
      tv_name.setText(names[arg0]);
//    }
    return convertView;
  }
  
  
}
