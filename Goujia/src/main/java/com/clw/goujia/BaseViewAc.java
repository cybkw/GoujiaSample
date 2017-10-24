package com.clw.goujia;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectPLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.baike.BaikeAc;
import com.clw.goujia.cases.CaseAc;
import com.clw.goujia.home.HomeAc;
import com.clw.goujia.home.PersonalAc;

/**
 * Activity界面基类
 * 
 * 
 */
@InjectPLayer(R.layout.ac_base_view)
public abstract class BaseViewAc extends BaseAc {
  private final String TAG = "BaseViewAc";

  // @InjectView
  private RadioButton rb_1, rb_2, rb_3, rb_4;

  @InjectView
  private FrameLayout ll_home_bottom_menu;

  /** 双击退出 */
  private boolean isSecondTime = false;
  private Handler mExitHandler = new Handler() {
    public void handleMessage(android.os.Message msg) {
      isSecondTime = false;
    };
  };

  /**
   * 初始化
   * 
   * */
  @InjectInit
  private void init() {
    /* 标题 */
    // tv_title.setText(getAppTitle());
    rb_1 = (RadioButton) ll_home_bottom_menu.findViewById(R.id.rb_1);
    rb_2 = (RadioButton) ll_home_bottom_menu.findViewById(R.id.rb_2);
    rb_3 = (RadioButton) ll_home_bottom_menu.findViewById(R.id.rb_3);
    rb_4 = (RadioButton) ll_home_bottom_menu.findViewById(R.id.rb_4);

    if (0 == getAcIndex()) {
      /* 不是一级页面 没有下导航 */
      ll_home_bottom_menu.setVisibility(View.GONE);
    } else {
      ll_home_bottom_menu.setVisibility(View.VISIBLE);
    }

  }

  @Override
  public void onBackPressed() {
    System.out.println("getAcIndex==" + getAcIndex());
    if (0 == getAcIndex()) { // 不是一级页面
      super.onBackPressed();
    } else {
      if (isSecondTime) {
        App.exit(0);
      } else {
        showToast("再按一次退出应用!");
        //
        isSecondTime = true;
        mExitHandler.postDelayed(new Runnable() {
          @Override
          public void run() {
            mExitHandler.sendEmptyMessage(0);
          }
        }, 2000);
      }
    }
  }

  @Override
  protected void onResume() {
    // TODO Auto-generated method stub
    super.onResume();
    setBottmBtn();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
  }

  /**
   * 设置底部按钮
   * 
   */
  private void setBottmBtn() {
    if (1 == getAcIndex()) {
      rb_1.setChecked(true);
    } else if (2 == getAcIndex()) {
      rb_2.setChecked(true);
    } else if (3 == getAcIndex()) {
      rb_3.setChecked(true);
    } else if (4 == getAcIndex()) {
      rb_4.setChecked(true);
    }
  }

  @Override
  public void onBtnClick(View v) {
    switch (v.getId()) {
      case R.id.rb_1:
        /* 跳转主页 */
        go2MenuAc(v, HomeAc.class);
        break;
      case R.id.rb_2:
        /* 跳转案例 */
        go2MenuAc(v, CaseAc.class);
        break;
      case R.id.rb_3:
        /* 跳转百科 */
        go2MenuAc(v, BaikeAc.class);
        break;
      case R.id.rb_4:
        /* 跳转个人中心 */
        go2MenuAc(v, PersonalAc.class);
        break;
    }
  }

  /**
   * 底部menu 跳转Activity
   * 
   * @param clazz
   */
  private void go2MenuAc(View v, Class<?> clazz) {
    if (getAcIndex() != Integer.parseInt(v.getTag().toString())) {
      startAc(new Intent(mActivity, clazz));
      // finish();
    }
  }

  /**
   * 设置一级页面编号
   * 
   * @return
   */
  protected abstract int getAcIndex();

  /**
   * 设置标题
   * 
   * @return
   */
  protected abstract String getAppTitle();

  /**
   * 设置左侧ImageBtn的文字
   * 
   * @return
   */
  // protected abstract int getImageBtnLeft();

  // protected abstract void getImageBtnLeftClick();

  /**
   * 设置右侧Button的文字
   * 
   * @return
   */
  // protected abstract String getButton();

  // protected abstract void getButtonClick();

  /**
   * 设置右边ImageButton图片
   * 
   * @return
   */
  // protected abstract int getImageBtnSrc();

  // protected abstract void getImageBtnClick();
}
