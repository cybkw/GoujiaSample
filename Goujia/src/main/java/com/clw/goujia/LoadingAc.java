package com.clw.goujia;

import com.clw.goujia.home.HomeAc;
import com.clw.goujia.utils.StaticField;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoadingAc extends BaseAc {
  private final String TAG = "LoadingAc";

  private SharedPreferences preferences;

  @Override
  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    setContentView(R.layout.ac_loading);
    mActivity = LoadingAc.this;
    init();
  }

  private int isFirst = 0;

  private void init() {

    /* 获取屏幕数据 */
    StaticField.SCREEN_WIDHT = getResources().getDisplayMetrics().widthPixels;
    StaticField.SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;

    /* 是否为第一次启动 */
    preferences = getSharedPreferences("first_launcher", Context.MODE_PRIVATE);
    isFirst = preferences.getInt("isfirst", 0);

    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        mHandler.sendEmptyMessage(0);
      }
    }, 2000);

  }

  private Handler mHandler = new Handler() {
    public void handleMessage(android.os.Message msg) {
//      if (isFirst == 0) {
//        Log.i(TAG, "...");
//        Editor ed = preferences.edit();
//        ed.putInt("isfirst", 1);
//        ed.commit();
//        go2WelcomeAc();
//      } else {
        go2HomeAc();
//      }
    };
  };

  /**
   * @category 跳转首页
   */
  private void go2HomeAc() {
    Log.i(TAG, "跳转首页");
    startAcMove(new Intent(mActivity, HomeAc.class));
    finish();
  }

  private void go2WelcomeAc() {
    startAcMove(new Intent(mActivity, WelcomeAc.class));
    finish();
  }
}
