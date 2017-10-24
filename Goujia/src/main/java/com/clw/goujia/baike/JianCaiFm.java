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

public class JianCaiFm extends Fragment implements IXListViewListener, OnItemClickListener {
  private static final String TAG = "JianCaiFm";

  private Context mContext;

  /** 图文列表 */
  private XListView xlv_jiancai;

  private View contentView;

  private BaikeAdapter mBaikeAdapter = null;

  private List<Baike> mBaikes = new ArrayList<Baike>();

  public JianCaiFm(Context mContext) {
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

    xlv_jiancai = (XListView) contentView.findViewById(R.id.xlv_tuijian);
    xlv_jiancai.setXListViewListener(this);
    
    Baike baike = new Baike();
    baike.setTitle("建材");
    baike.setInfo("2016年3月一年一度的家电盛宴");
    baike.setImg_url(R.drawable.image_jc1);
    mBaikes.add(baike);

    mBaikeAdapter = new BaikeAdapter(mContext, mBaikes);
    xlv_jiancai.setAdapter(mBaikeAdapter);
    mBaikeAdapter.notifyDataSetChanged();

    xlv_jiancai.setOnItemClickListener(this);
  }

  @Override
  public void onRefresh() {
    Tools.xlvStopLoad(xlv_jiancai);
  }

  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_jiancai);
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    startActivity(new Intent(mContext, JianCaiAc.class));
  }
}
