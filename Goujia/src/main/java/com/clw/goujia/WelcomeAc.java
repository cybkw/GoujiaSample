package com.clw.goujia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.clw.goujia.adapter.WelcomeAdapter;
import com.clw.goujia.home.HomeAc;
import com.clw.goujia.utils.StaticField;

/**
 * 引导图
 * */
public class WelcomeAc extends BaseAc implements OnPageChangeListener, OnTouchListener {
  private static final String TAG = "WelcomeAc";

  private int[] loading_img_ids = { R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4 };

  private ViewPager vp_welcome;

  private boolean isCanGo;

  @Override
  protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    setContentView(R.layout.ac_welcome);
    
    init();
  }
  private void init() {
    mActivity = WelcomeAc.this;
    
    vp_welcome=(ViewPager) findViewById(R.id.vp_welcome);
    vp_welcome.setAdapter(new WelcomeAdapter(mActivity, loading_img_ids));
    vp_welcome.setOnTouchListener(this);
    vp_welcome.setOnPageChangeListener(this);
  }

  private void JumpPage() {
    startAcMove(new Intent(mActivity, HomeAc.class));
    finish();
  }

  @Override
  public boolean onTouch(View arg0, MotionEvent event) {
    Log.i(TAG, "event.getY()  === " + event.getY());
    if (MotionEvent.ACTION_DOWN == event.getAction() && isCanGo) {
      if (event.getY() >= StaticField.SCREEN_HEIGHT / 3 * 2) {
        JumpPage();
      }
    }
    return false;
  }

  @Override
  public void onPageScrollStateChanged(int arg0) {
  }

  @Override
  public void onPageScrolled(int arg0, float arg1, int arg2) {
  }

  @Override
  public void onPageSelected(int arg0) {
    if (arg0 == loading_img_ids.length - 1) {
      isCanGo = true;
    } else {
      isCanGo = false;
    }

    System.out.println("isCanGo === " + isCanGo);
  }
}
