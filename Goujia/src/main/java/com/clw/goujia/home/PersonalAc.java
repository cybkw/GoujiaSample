package com.clw.goujia.home;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseViewAc;
import com.clw.goujia.R;
import com.clw.goujia.setup.MyCollectAc;
import com.clw.goujia.setup.SetupAc;
import com.clw.goujia.utils.Tools;

/**
 * 个人中心
 * */
@InjectLayer(value = R.layout.ac_personal, parent = R.id.fl_content)
public class PersonalAc extends BaseViewAc implements OnClickListener {
  private static final String TAG = "PersonalAc";

  @InjectView
  private TextView tv_title;

  @InjectView
  private TextView tv_uname;

  @InjectView
  private TextView textDingdan, textShoucang, textShezhi, textGuanyu;

  @InjectView
  private RelativeLayout rl_collect;

  @InjectView
  private RelativeLayout rl_order;

  @InjectView
  private RelativeLayout rl_setup;

  @InjectView
  private RelativeLayout rl_about;

  @InjectView
  private LinearLayout layout_login;

  @InjectView
  private Button btn_login;

  @InjectView
  private Button btn_regit;

  @InjectInit
  private void init() {
    mActivity = PersonalAc.this;

    App.addActivity(mActivity);
    Tools.setFontStyle(mActivity, tv_title, textDingdan, textGuanyu, textShezhi, textShoucang, tv_uname);
    rl_about.setOnClickListener(this);
    rl_collect.setOnClickListener(this);
    rl_order.setOnClickListener(this);
    rl_setup.setOnClickListener(this);
    btn_login.setOnClickListener(this);
    btn_regit.setOnClickListener(this);

  }

  @Override
  protected void onResume() {
    super.onResume();
//    if (App.isLogin) {
//      layout_login.setVisibility(View.GONE);
//      tv_uname.setVisibility(View.VISIBLE);
//    } else {
//      tv_uname.setVisibility(View.GONE);
//      layout_login.setVisibility(View.VISIBLE);
//    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        startAcButtomIn(new Intent(mActivity, LoginAc.class));
        break;
      case R.id.btn_regit:
        break;
      case R.id.rl_about:

        break;
      case R.id.rl_collect:
        startAcMove(new Intent(mActivity,MyCollectAc.class));
        break;
      case R.id.rl_order:

        break;
      case R.id.rl_setup:
        startAcMove(new Intent(mActivity, SetupAc.class));
        break;
      default:
        break;
    }
  }

  @Override
  protected int getAcIndex() {
    return 4;
  }

  @Override
  protected String getAppTitle() {
    return null;
  }

}
