package com.clw.goujia.adapter;

import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.bean.City;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HotCityAdapter extends BaseAdapter {
  private static final String TAG = "HotCityAdapter";

  private Context mContext;

  private List<City> citys;

  public HotCityAdapter(Context mContext, List<City> citys) {
    this.mContext = mContext;
    this.citys = citys;
  }

  @Override
  public int getCount() {
    return citys.size();
  }

  @Override
  public Object getItem(int arg0) {
    return citys.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return arg0;
  }

  @Override
  public View getView(int arg0, View arg1, ViewGroup arg2) {
    Views views;
    if (arg1 == null) {
      views = new Views();
      arg1 = LayoutInflater.from(mContext).inflate(R.layout.item_hot_city, null);
      views.tv_cityName = (TextView) arg1.findViewById(R.id.tv_cityName);
      arg1.setTag(views);
    } else {
      views = (Views) arg1.getTag();
    }

    views.tv_cityName.setText(citys.get(arg0).getName());

    return arg1;
  }

  private class Views {
    TextView tv_cityName;
  }
}
