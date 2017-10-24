package com.clw.goujia.cases;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

/**
 * 案例详情
 * */
public class CaseDetailsAc extends BaseAc implements OnClickListener {
  private static final String TAG = "CaseDetailsAc";

  private ImageButton ib_back;

  private TextView tv_title;

  private String mType = "";

  private Intent mIntent = null;

  @Override
  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    mIntent = getIntent();
    mActivity = CaseDetailsAc.this;

    mType = mIntent.getExtras().getString("type");
    if (!Tools.isNull(mType)) {
      setContentView(R.layout.ac_case_details);
    } else {
      setContentView(R.layout.ac_case_details_02);
    }
    initView();

  }

  private void initView() {
    
    tv_title=(TextView) findViewById(R.id.tv_title);
    ib_back=(ImageButton) findViewById(R.id.ib_back);
    
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
