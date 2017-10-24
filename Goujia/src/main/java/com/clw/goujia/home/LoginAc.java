package com.clw.goujia.home;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

/**
 * 登录
 * */
@InjectLayer(R.layout.ac_login)
public class LoginAc extends BaseAc implements OnClickListener {
  private static final String TAG = "LoginAc";

  @InjectView
  private EditText et_username;

  @InjectView
  private EditText et_password;

  @InjectView
  private Button btn_login;

  @InjectView
  private Button btn_regit;

  @InjectView
  private TextView tv_forget;

  @InjectInit
  private void init() {
    mActivity = LoginAc.this;

    btn_login.setOnClickListener(this);
    btn_regit.setOnClickListener(this);
    tv_forget.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        doLogin();
        break;

      default:
        break;
    }
  }

  /**
   * 登录
   * */
  private void doLogin() {
    String uname = et_username.getText().toString();
    String pword = et_password.getText().toString();

    if (Tools.isNull(uname)) {
      showToast("请输入用户名");
      return;
    }
    if (Tools.isNull(pword)) {
      showToast("请输入密码");
      return;
    }
    if (!Tools.isNull(pword,uname)) {
//      startAcButtom(new Intent(mActivity,));
      finishAcScale();
      App.isLogin=true;
    }
  }
}
