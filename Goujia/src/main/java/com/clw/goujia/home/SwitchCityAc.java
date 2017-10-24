package com.clw.goujia.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.CityAdapter;
import com.clw.goujia.adapter.HotCityAdapter;
import com.clw.goujia.bean.City;
import com.clw.goujia.db.DBHelper;
import com.clw.goujia.db.DatabaseHelper;
import com.clw.goujia.ui.MyGridView;
import com.clw.goujia.ui.MyLetterListView;
import com.clw.goujia.ui.MyLetterListView.OnTouchingLetterChangedListener;
import com.clw.goujia.utils.StaticField;
import com.clw.goujia.utils.Tools;
import com.zdp.aseo.content.AseoZdpAseo;

/**
 * 切换城市
 * */
@InjectLayer(R.layout.ac_switchcity)
public class SwitchCityAc extends BaseAc implements OnClickListener {
  private static final String TAG = "SwitchCityAc";

  // 城市列表头部
  private LinearLayout layout_head;

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private TextView tv_title;

  @InjectView
  private EditText et_key;

  // 所有城市
  @InjectView
  private ListView lv_allcity;
  private ArrayList<City> mAllCitys = new ArrayList<City>();
  private CityAdapter mCityAdapter = null;

  // 搜索城市
  @InjectView
  private ListView lv_city_search;
  private ArrayList<City> mResultcitys = new ArrayList<City>();
  private CityAdapter mSearchCityAdapter = null;

  // 热门城市
  private MyGridView mgv_hot;
  private ArrayList<City> mHotCitys = new ArrayList<City>();
  private HotCityAdapter mHotCityAdapter = null;

  // 定位城市
  private TextView tv_city, textdingwei, textremen, textsuoyou;

  private String mKey="";

  @InjectInit
  private void init() {
    mActivity = SwitchCityAc.this;

    layout_head = (LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.head_city, null);
    mgv_hot = (MyGridView) layout_head.findViewById(R.id.mgv_hot_city);
    tv_city = (TextView) layout_head.findViewById(R.id.tv_city);
    textdingwei = (TextView) layout_head.findViewById(R.id.textdingwei);
    textremen = (TextView) layout_head.findViewById(R.id.textremen);
    textsuoyou = (TextView) layout_head.findViewById(R.id.textsuoyou);
    ib_back.setOnClickListener(this);

    Tools.setFontStyle(mActivity, tv_city, tv_title, textdingwei, textremen, textsuoyou);

    lv_allcity.addHeaderView(layout_head);

    mgv_hot.setOnItemClickListener(new HotCityOnItemClick());
    lv_allcity.setOnItemClickListener(new AllOnItemClick());
    lv_city_search.setOnItemClickListener(new SearchItemClick());
    mHotCityAdapter = new HotCityAdapter(mActivity, mHotCitys);
    mgv_hot.setAdapter(mHotCityAdapter);
    mCityAdapter = new CityAdapter(mActivity, mAllCitys);
    lv_allcity.setAdapter(mCityAdapter);
    mSearchCityAdapter = new CityAdapter(mActivity, mResultcitys);
    lv_city_search.setAdapter(mSearchCityAdapter);

    initHotCity();
    // initAllCity();
    mAllCitys.addAll(getCityList());
    mCityAdapter.notifyDataSetChanged();

    et_key.addTextChangedListener(new TextKey());

  }

  /**
   * EditText 输入监听
   * */
  private class TextKey implements TextWatcher {
    @Override
    public void onTextChanged(CharSequence str, int arg1, int arg2, int arg3) {
      mKey=et_key.getText().toString();
      if (!Tools.isNull(mKey)) {
        showToast(mKey);
        getResultCityList(mKey);
        lv_city_search.setVisibility(View.VISIBLE);
        lv_allcity.setVisibility(View.GONE);
      }else{
        mResultcitys.clear();
        lv_city_search.setVisibility(View.GONE);
        lv_allcity.setVisibility(View.VISIBLE);
      }
    }

    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }
  }

  /**
   * 查询数据库
   * */
  private void getResultCityList(String keyword) {
    DBHelper dbHelper = new DBHelper(this);
    try {
      dbHelper.createDataBase();
      SQLiteDatabase db = dbHelper.getWritableDatabase();
      Cursor cursor = db.rawQuery("select * from city where name like \"%" + keyword + "%\" or pinyin like \"%"
          + keyword + "%\"", null);
      City city;
      Log.e("info", "length = " + cursor.getCount());
      if (cursor.getCount() > 0) {
        while (cursor.moveToNext()) {
          city = new City(cursor.getString(1), cursor.getString(2));
          mResultcitys.add(city);
        }
        cursor.close();
        db.close();
        mSearchCityAdapter.notifyDataSetChanged();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 添加全部城市
   * */
  private ArrayList<City> getCityList() {
    DBHelper dbHelper = new DBHelper(this);
    ArrayList<City> list = new ArrayList<City>();
    try {
      dbHelper.createDataBase();
      SQLiteDatabase db = dbHelper.getWritableDatabase();
      Cursor cursor = db.rawQuery("select * from city", null);
      City city;
      while (cursor.moveToNext()) {
        city = new City(cursor.getString(1), cursor.getString(2));
        list.add(city);
      }
      cursor.close();
      db.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Collections.sort(list, comparator);
    return list;
  }

  /**
   * a-z排序
   */
  @SuppressWarnings("rawtypes")
  Comparator comparator = new Comparator<City>() {
    @Override
    public int compare(City lhs, City rhs) {
      String a = lhs.getPinyin().substring(0, 1);
      String b = rhs.getPinyin().substring(0, 1);
      int flag = a.compareTo(b);
      if (flag == 0) {
        return a.compareTo(b);
      } else {
        return flag;
      }
    }
  };

  /**
   * 添加热门城市
   * */
  private void initHotCity() {
    City city = new City("上海", "2");
    mHotCitys.add(city);
    city = new City("北京", "2");
    mHotCitys.add(city);
    city = new City("广州", "2");
    mHotCitys.add(city);
    city = new City("深圳", "2");
    mHotCitys.add(city);
    city = new City("武汉", "2");
    mHotCitys.add(city);
    city = new City("天津", "2");
    mHotCitys.add(city);
    city = new City("西安", "2");
    mHotCitys.add(city);
    city = new City("南京", "2");
    mHotCitys.add(city);
    city = new City("杭州", "2");
    mHotCitys.add(city);
    city = new City("成都", "2");
    mHotCitys.add(city);
    city = new City("重庆", "2");
    mHotCitys.add(city);
    mHotCityAdapter.notifyDataSetChanged();
  }

  /**
   * 热门城市点击事件
   * */
  private class HotCityOnItemClick implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

      // showToast(mHotCitys.get(arg2).getName());
      App.AREA = mHotCitys.get(arg2).getName();
      finishAcMove();
    }
  }

  private class AllOnItemClick implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

      App.AREA = mAllCitys.get(arg2 - 1).getName();
      finishAcMove();
    }
  }

  private class SearchItemClick implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
      App.AREA = mResultcitys.get(arg2).getName();
      finishAcMove();
      // showToast(mResultcitys.get(arg2).getName());
    }
  }

  @Override
  public void onClick(View arg0) {
    switch (arg0.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;

      default:
        break;
    }
  }

}
