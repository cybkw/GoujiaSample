package com.clw.goujia.cases;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;
/**
 * 施工保障
 * */
@InjectLayer(R.layout.ac_shigong)
public class ShiGongAc extends BaseAc implements OnClickListener{
  private static final String TAG = "ShiGongAc";
  
  @InjectView 
  private ImageButton ib_back;
  
  @InjectView
  private TextView tv_title;
  
  @InjectInit
  private void init(){
    mActivity=ShiGongAc.this;
    
    Tools.setFontStyle(mActivity, tv_title);
    ib_back.setOnClickListener(this);
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
  
}
