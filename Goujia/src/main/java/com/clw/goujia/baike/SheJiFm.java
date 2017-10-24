package com.clw.goujia.baike;

import java.util.ArrayList;
import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.adapter.BaikeAdapter;
import com.clw.goujia.adapter.ShejiAdapter;
import com.clw.goujia.bean.Baike;
import com.clw.goujia.ui.XListView;
import com.clw.goujia.ui.XListView.IXListViewListener;
import com.clw.goujia.utils.Tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class SheJiFm extends Fragment implements IXListViewListener, OnItemClickListener {
  private static final String TAG = "SheJiFm";

  private Context mContext;

  /** 图文列表 */
  private XListView xlv_sheji;

  private View contentView;

  private BaikeAdapter mShejiAdapter = null;

  private List<Baike> mBaikes = new ArrayList<Baike>();

  public SheJiFm(Context mContext) {
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

    xlv_sheji = (XListView) contentView.findViewById(R.id.xlv_tuijian);
    xlv_sheji.setXListViewListener(this);
    
    
    Baike baike = new Baike();
    baike.setTitle("设计");
    baike.setInfo("设计是从人类生活的开始");
    baike.setImg_url(R.drawable.image_sj1);
    mBaikes.add(baike);

    mShejiAdapter = new BaikeAdapter(mContext, mBaikes);
    xlv_sheji.setAdapter(mShejiAdapter);
    xlv_sheji.setOnItemClickListener(this);
    mShejiAdapter.notifyDataSetChanged();
  }

  @Override
  public void onRefresh() {
    Tools.xlvStopLoad(xlv_sheji);
  }

  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_sheji);
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    startActivity(new Intent(mContext, ShejiAc.class));
  }
}
