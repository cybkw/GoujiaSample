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

public class CityAdapter extends BaseAdapter {
  private static final String TAG = "CityAdapter";

  private Context mContext;

  private List<City> cities;

  public CityAdapter(Context mContext, List<City> cities) {
    this.mContext = mContext;
    this.cities = cities;
  }

  @Override
  public int getCount() {
    return cities.size();
  }

  @Override
  public Object getItem(int arg0) {
    return cities.get(arg0);
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
      arg1 = LayoutInflater.from(mContext).inflate(R.layout.item_city, null);
      views.tv_name = (TextView) arg1.findViewById(R.id.tv_name);
      views.tv_alpha = (TextView) arg1.findViewById(R.id.tv_alpha);
      arg1.setTag(views);
    } else {
      views = (Views) arg1.getTag();
    }

    views.tv_name.setText(cities.get(arg0).getName());
//    String currentStr = cities.get(arg0).getPinyin();
//    String previewStr = (arg0 - 1) >= 0 ? cities.get(arg0 - 1).getPinyin() : " ";
//    if (!previewStr.equals(currentStr)) {
//      views.tv_alpha.setVisibility(View.VISIBLE);
//      views.tv_alpha.setText(currentStr);
//    } else {
//      views.tv_alpha.setVisibility(View.GONE);
//    }
    return arg1;
  }

  private class Views {
    TextView tv_name;
    TextView tv_alpha;
  }
}
