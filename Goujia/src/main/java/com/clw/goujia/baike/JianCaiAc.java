package com.clw.goujia.baike;

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
 * 建材详情
 * */
@InjectLayer(R.layout.ac_jiancai)
public class JianCaiAc extends BaseAc implements OnClickListener{
  private static final String TAG = "JianCaiAc";
  
  @InjectView
  private ImageButton ib_back;
  
  @InjectView
  private TextView tv_title;
  
  @InjectInit
  private void init(){
    mActivity=JianCaiAc.this;
    
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
