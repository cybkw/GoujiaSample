package com.clw.goujia.community;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.ComMenuAdapter;
import com.clw.goujia.utils.Tools;

@InjectLayer(R.layout.ac_community)
public class CommunityAc extends BaseAc implements OnClickListener, OnItemClickListener {
  private static final String TAG = "CommunityAc";

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private TextView tv_title;

  @InjectView
  private ListView lv_menu;

  private int[] images = new int[] { R.drawable.ic_shequ1, R.drawable.ic_shequ2, R.drawable.ic_shequ3 };

  private String[] names = new String[] { "楼盘特色社区", "公用社区", "社群" };

  private ComMenuAdapter mComMenuAdapter = null;

  @InjectInit
  private void init() {
    mActivity = CommunityAc.this;

    Tools.setFontStyle(mActivity, tv_title);
    ib_back.setOnClickListener(this);

    mComMenuAdapter = new ComMenuAdapter(mActivity, names, images);
    lv_menu.setAdapter(mComMenuAdapter);

    lv_menu.setOnItemClickListener(this);

  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    switch (images[arg2]) {
      case R.drawable.ic_shequ1: // 楼盘社区
        startAcMove(new Intent(mActivity, SpecialComAc.class));
        break;
      case R.drawable.ic_shequ2: // 公用社区
        showToast("未开放");
        break;
      case R.drawable.ic_shequ3:// 社群
        showToast("未开放");
        break;
      default:
        break;
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
