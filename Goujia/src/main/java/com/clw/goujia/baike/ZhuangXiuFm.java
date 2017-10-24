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

public class ZhuangXiuFm extends Fragment implements IXListViewListener, OnItemClickListener {
  private static final String TAG = "ZhuangXiuFm";

  private Context mContext;

  /** 图文列表 */
  private XListView xlv_zhuangxiu;

  private View contentView;

  private BaikeAdapter mBaikeAdapter = null;

  private List<Baike> mBaikes = new ArrayList<Baike>();

  public ZhuangXiuFm(Context mContext) {
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

    xlv_zhuangxiu = (XListView) contentView.findViewById(R.id.xlv_tuijian);
    xlv_zhuangxiu.setXListViewListener(this);
    
      Baike baike = new Baike();
      baike.setTitle("装修");
      baike.setInfo("选用绿色建材进行装修");
      baike.setImg_url(R.drawable.image_zxbk1);
      mBaikes.add(baike);

    mBaikeAdapter = new BaikeAdapter(mContext, mBaikes);
    xlv_zhuangxiu.setAdapter(mBaikeAdapter);
    xlv_zhuangxiu.setOnItemClickListener(this);
    mBaikeAdapter.notifyDataSetChanged();
  }

  @Override
  public void onRefresh() {
    Tools.xlvStopLoad(xlv_zhuangxiu);
  }

  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_zhuangxiu);
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    startActivity(new Intent(mContext, ZhuangXiuBkAc.class));
  }
}
