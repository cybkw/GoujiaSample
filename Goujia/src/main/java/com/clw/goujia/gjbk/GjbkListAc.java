package com.clw.goujia.gjbk;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.GjbkAdapter;
import com.clw.goujia.ui.XListView;
import com.clw.goujia.ui.XListView.IXListViewListener;
import com.clw.goujia.utils.Tools;

/**
 * 构家爆款列表
 * */
@InjectLayer(R.layout.ac_gjbk_list)
public class GjbkListAc extends BaseAc implements OnClickListener, IXListViewListener, OnItemClickListener {
  private static final String TAG = "GjbkListAc";

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private XListView xlv_gjbk;
  
  @InjectView
  private TextView tv_title;

  /** 适配器 */
  private GjbkAdapter mGjbkAdapter = null;

  @InjectInit
  private void init() {
    mActivity = GjbkListAc.this;

    
    Tools.setFontStyle(mActivity, tv_title);
    ib_back.setOnClickListener(this);
    xlv_gjbk.setXListViewListener(this);
    xlv_gjbk.setOnItemClickListener(this);
    xlv_gjbk.setPullLoadEnable(true);

    List<String> list = new ArrayList<String>();
    for (int i = 0; i < 3; i++) {
      list.add("测试");
    }
    
    mGjbkAdapter = new GjbkAdapter(mActivity, list);
    xlv_gjbk.setAdapter(mGjbkAdapter);
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    startAcMove(new Intent(mActivity,GjbkDetails.class));
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;

      default:
        break;
    }
  }

  /**
   * 下拉刷新
   * */
  @Override
  public void onRefresh() {
    Tools.xlvStopLoad(xlv_gjbk);
  }

  /**
   * 上拉加载
   * */
  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_gjbk);
    xlv_gjbk.setFooterHint("没有更多了");
  }

}
