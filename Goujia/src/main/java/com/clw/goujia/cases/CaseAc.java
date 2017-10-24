package com.clw.goujia.cases;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseViewAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.CaseAdapter;
import com.clw.goujia.bean.Baike;
import com.clw.goujia.ui.XListView;
import com.clw.goujia.ui.XListView.IXListViewListener;
import com.clw.goujia.utils.Tools;

/**
 * 案例
 * */
@InjectLayer(value = R.layout.ac_case, parent = R.id.fl_content)
public class CaseAc extends BaseViewAc implements OnItemClickListener, IXListViewListener {
  private static final String TAG = "CaseAc";

  @InjectView
  private TextView tv_title;

  @InjectView
  private XListView xlv_case;

  private CaseAdapter mCaseAdapter = null;

  private List<Baike> cases = new ArrayList<Baike>();

  @InjectInit
  private void init() {
    mActivity = CaseAc.this;

    App.addActivity(mActivity);
    Tools.setFontStyle(mActivity, tv_title);

    xlv_case.setOnItemClickListener(this);
    xlv_case.setXListViewListener(this);
    xlv_case.setPullLoadEnable(true);

    Baike baike = new Baike();
    baike.set_id("1");
    baike.setImg_url(R.drawable.al_01);
    baike.setTitle("杭州天城府现代简约");
    cases.add(baike);
    Baike baike2 = new Baike();
    baike2.set_id("2");
    baike2.setImg_url(R.drawable.al_1);
    baike2.setTitle("临安滨湖天地田园风格");
    cases.add(baike2);

    mCaseAdapter = new CaseAdapter(mActivity, cases);
    xlv_case.setAdapter(mCaseAdapter);
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

    if (cases.get(arg2-1).get_id().equals("1")) {
      startAcMove(new Intent(mActivity, CaseDetailsAc.class).putExtra("type", "1"));
    } else {
      startAcMove(new Intent(mActivity, CaseDetailsAc.class).putExtra("type", ""));
    }
  }

  @Override
  public void onRefresh() {
    Tools.xlvStopLoad(xlv_case);
  }

  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_case);
    xlv_case.setFooterHint("没有更多了");
  }

  @Override
  protected int getAcIndex() {
    return 2;
  }

  @Override
  protected String getAppTitle() {
    return null;
  }

}
