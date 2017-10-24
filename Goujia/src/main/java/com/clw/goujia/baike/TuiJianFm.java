package com.clw.goujia.baike;

import java.util.ArrayList;
import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.adapter.BaikeAdapter;
import com.clw.goujia.bean.Baike;
import com.clw.goujia.ui.XListView;
import com.clw.goujia.ui.XListView.IXListViewListener;
import com.clw.goujia.utils.Tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class TuiJianFm extends Fragment implements IXListViewListener,OnItemClickListener {
  private static final String TAG = "TuiJianFm";

  private Context mContext;

  /** 图文列表 */
  private XListView xlv_tuijian;

  private View contentView;

  private BaikeAdapter mBaikeAdapter = null;

  private List<Baike> mBaikes = new ArrayList<Baike>();

  public TuiJianFm(Context mContext) {
    this.mContext = mContext;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    contentView = LayoutInflater.from(mContext).inflate(R.layout.fm_tuijian, null);

    init();
    return contentView;
  }

  /**
   * 初始化
   * */
  private void init() {

    xlv_tuijian = (XListView) contentView.findViewById(R.id.xlv_tuijian);
    
    xlv_tuijian.setXListViewListener(this);
    xlv_tuijian.setOnItemClickListener(this);

    Baike baike = new Baike();
    baike.set_id("1");
    baike.setTitle("装修");
    baike.setInfo("选用绿色建材进行装修");
    baike.setImg_url(R.drawable.image_zxbk1);
    mBaikes.add(baike);
    Baike baike2 = new Baike();
    baike.set_id("2");
    baike2.setTitle("设计");
    baike2.setInfo("设计是从人类生活的开始");
    baike2.setImg_url(R.drawable.image_sj1);
    mBaikes.add(baike2);
    Baike baike3 = new Baike();
    baike.set_id("3");
    baike3.setTitle("建材");
    baike3.setInfo("2016年3月一年一度的家电盛宴");
    baike3.setImg_url(R.drawable.image_jc1);
    mBaikes.add(baike3);

    mBaikeAdapter = new BaikeAdapter(mContext, mBaikes);
    xlv_tuijian.setAdapter(mBaikeAdapter);
  }

  @Override
  public void onRefresh() {
    Tools.xlvStopLoad(xlv_tuijian);
  }

  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_tuijian);
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    switch (arg2) {
      case 1:
        startActivity(new Intent(mContext,ZhuangXiuBkAc.class));
        break;
      case 2:
        startActivity(new Intent(mContext,ShejiAc.class));
        break;
      case 3:
        startActivity(new Intent(mContext,JianCaiAc.class));
        break;
      default:
        break;
    }
  }
}
