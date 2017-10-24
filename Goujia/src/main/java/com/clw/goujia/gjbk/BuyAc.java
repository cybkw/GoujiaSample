package com.clw.goujia.gjbk;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

/**
 * 选择店铺--->购买
 * */
@InjectLayer(R.layout.ac_buy)
public class BuyAc extends BaseAc implements OnClickListener {
  private static final String TAG = "BuyAc";

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private TextView tv_title;

  @InjectView
  private TextView tv_submit;
  
  //价格
  @InjectView
  private TextView tv_total;
  
  //计算
  @InjectView
  private TextView btn_compute;

  // 手机号
  @InjectView
  private EditText et_phone;
  private String mPhone = "";

  // 称呼
  @InjectView
  private EditText et_nickname;
  private String mNickname = "";

  // 所在小区
  @InjectView
  private EditText et_houses;
  private String mHouse = "";

  // 装修面积
  @InjectView
  private EditText et_area;
  private String mArea = "";

  @InjectInit
  private void init() {
    mActivity = BuyAc.this;

    Tools.setFontStyle(mActivity, tv_submit, tv_title);
    ib_back.setOnClickListener(this);
    tv_submit.setOnClickListener(this);
    btn_compute.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;
      case R.id.tv_submit:
        mArea = et_area.getText().toString();
        mHouse = et_houses.getText().toString();
        mNickname = et_nickname.getText().toString();
        mPhone = et_phone.getText().toString();
        if (!Tools.isNull(mHouse, mArea, mNickname, mPhone)) {

        } else {
          if (Tools.isNull(mArea)) {
            et_area.requestFocus();
          }

          if (Tools.isNull(mHouse)) {
            et_houses.requestFocus();
          }

          if (Tools.isNull(mNickname)) {
            et_nickname.requestFocus();
          }

          if (Tools.isNull(mPhone)) {
            et_phone.requestFocus();
          }
          
        }
        break;
      case R.id.btn_compute:
        if (!Tools.isNull(mArea)) {
          boolean area=Boolean.parseBoolean(mArea);
          
        }
        
        break;
      default:
        break;
    }
  }
}
