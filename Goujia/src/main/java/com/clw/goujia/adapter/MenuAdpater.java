package com.clw.goujia.adapter;

import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdpater extends BaseAdapter {
  private static final String TAG = "MenuAdpater";

  private Context mContext;

  private int[] images;

  private String[] names;

  public MenuAdpater(Context mContext, int[] images, String[] names) {
    this.mContext = mContext;
    this.images = images;
    this.names = names;
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
  public View getView(int arg0, View views, ViewGroup arg2) {

    views = LayoutInflater.from(mContext).inflate(R.layout.item_menu, null);
    TextView tv_name = (TextView) views.findViewById(R.id.tv_menu_name);
    ImageView iv_menu = (ImageView) views.findViewById(R.id.iv_menu_img);

    iv_menu.setImageResource(images[arg0]);
    tv_name.setText(names[arg0]);
//    if (arg0 != 0) {
//      tv_name.setTextSize(TypedValue.COMPLEX_UNIT_PX,15);
//    }

    Tools.setFontStyle(mContext, tv_name);
    return views;
  }

}
