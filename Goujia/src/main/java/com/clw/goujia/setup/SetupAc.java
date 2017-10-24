package com.clw.goujia.setup;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

/**
 * 设置
 * */
@InjectLayer(R.layout.ac_setup)
public class SetupAc extends BaseAc implements OnClickListener{
  private static final String TAG = "SetupAc";
  
  @InjectView
  private ImageButton ib_back;
  
  @InjectView
  private Button btn_exit;
  
  @InjectView
  private RelativeLayout rl_jianyi,rl_clear,rl_update;
  
  @InjectView
  private TextView tv_title,text1,text2,text3;
  
  @InjectInit
  private void init(){
    mActivity=SetupAc.this;
    
    Tools.setFontStyle(mActivity, tv_title,text1,text2,text3);
    ib_back.setOnClickListener(this);
    rl_clear.setOnClickListener(this);
    rl_jianyi.setOnClickListener(this);
    rl_update.setOnClickListener(this);
    btn_exit.setOnClickListener(this);
  }

  @Override
  public void onClick(View arg0) {
    switch (arg0.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;
      case R.id.btn_exit:
        App.isLogin=false;
        finishAcMove();
        break;
      case R.id.rl_jianyi:
        startAcMove(new Intent(mActivity,FeedBackAc.class));
        break;
      default:
        break;
    }
  }
}
