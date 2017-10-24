package com.clw.goujia.setup;

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

@InjectLayer(R.layout.ac_feedback)
public class FeedBackAc extends BaseAc implements OnClickListener{
  private static final String TAG = "FeedBackAc";
  
  @InjectView
  private TextView tv_title;
  
  @InjectView
  private TextView tv_send;
  
  @InjectView
  private ImageButton ib_back;
  
  @InjectView
  private EditText et_content;
  
  @InjectInit
  private void init(){
    mActivity=FeedBackAc.this;
    
    Tools.setFontStyle(mActivity, tv_send,tv_title);
    tv_send.setOnClickListener(this);
    ib_back.setOnClickListener(this);
  }

  @Override
  public void onClick(View arg0) {
    switch (arg0.getId()) {
      case R.id.tv_send:
        String content=et_content.getText().toString();
        if (!Tools.isNull(content)) {
          showToast("发送成功");
          finishAcMove();
        }
        break;
      case R.id.ib_back:
        finishAcMove();
        break;
      default:
        break;
    }
  }
}
