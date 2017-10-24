package com.clw.goujia.baike;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseViewAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.MyFragmentPagerAdapter;
import com.clw.goujia.utils.StaticField;
import com.clw.goujia.utils.Tools;

/**
 * @category 百科
 * */
@InjectLayer(value = R.layout.ac_baike, parent = R.id.fl_content)
public class BaikeAc extends BaseViewAc implements OnClickListener {
  private static final String TAG = "BaikeAc";

  @InjectView
  private TextView tv_hint;

  @InjectView
  private RelativeLayout rl_search;

  @InjectView
  private TextView tv_tuijian, tv_zhuangxiu, tv_sheji, tv_jiancai;

  @InjectView
  private ViewPager vp_content;

  private ArrayList<Fragment> mFragmentsList;

  @InjectView
  private ImageView iv_bottom_line;

  private int mCurrIndex = 0;
  private int mPosition_one;
  private int mPosition_two;
  private int mPosition_three;
  private Resources mResources;


  @InjectInit
  private void init() {
    mActivity = BaikeAc.this;
    mResources = getResources();

    App.addActivity(mActivity);
    Tools.setFontStyle(mActivity, tv_hint, tv_jiancai, tv_sheji, tv_sheji, tv_tuijian, tv_zhuangxiu);

    rl_search.setOnClickListener(this);

    tv_tuijian.setOnClickListener(new SwitchFragMent(0));
    tv_zhuangxiu.setOnClickListener(new SwitchFragMent(1));
    tv_sheji.setOnClickListener(new SwitchFragMent(2));
    tv_jiancai.setOnClickListener(new SwitchFragMent(3));
  

    InitWidth();
    initViewPager();
    
    
  }
                        
  private void InitWidth() {
    LayoutParams lp = iv_bottom_line.getLayoutParams();
    lp.width = StaticField.SCREEN_WIDHT / 4;
    iv_bottom_line.setLayoutParams(lp);
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    int screenW = dm.widthPixels;

    mPosition_one = (int) (screenW / 4.0);
    mPosition_two = mPosition_one * 2;
    mPosition_three = mPosition_one * 3;
  }

  private void initViewPager() {
    vp_content = (ViewPager) findViewById(R.id.vp_content);
    mFragmentsList = new ArrayList<Fragment>();

    TuiJianFm tuijian = new TuiJianFm(mActivity);
    ZhuangXiuFm zhuangxiu = new ZhuangXiuFm(mActivity);
    SheJiFm sheji = new SheJiFm(mActivity);
    JianCaiFm jiancai = new JianCaiFm(mActivity);

    mFragmentsList.add(tuijian);
    mFragmentsList.add(zhuangxiu);
    mFragmentsList.add(sheji);
    mFragmentsList.add(jiancai);
    vp_content.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentsList));
    vp_content.setCurrentItem(0);
    vp_content.setOffscreenPageLimit(3);
    vp_content.setOnPageChangeListener(new MyOnPageChangeListener());
  }

  /**
   * @category 选项卡切换监听
   * */
  public class MyOnPageChangeListener implements OnPageChangeListener {

    @Override
    public void onPageSelected(int arg0) {
      Animation animation = null;
      switch (arg0) {
        case 0:
          if (mCurrIndex == 1) {
            animation = new TranslateAnimation(mPosition_one, 0, 0, 0);
            tv_zhuangxiu.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 2) {
            animation = new TranslateAnimation(mPosition_two, 0, 0, 0);
            tv_sheji.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 3) {
            animation = new TranslateAnimation(mPosition_three, 0, 0, 0);
            tv_jiancai.setTextColor(mResources.getColor(R.color.txt_black));
          }
          tv_tuijian.setTextColor(mResources.getColor(R.color.txt_red));
          break;
        case 1:
          if (mCurrIndex == 0) {
            animation = new TranslateAnimation(0, mPosition_one, 0, 0);
            tv_tuijian.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 2) {
            animation = new TranslateAnimation(mPosition_two, mPosition_one, 0, 0);
            tv_sheji.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 3) {
            animation = new TranslateAnimation(mPosition_three, mPosition_one, 0, 0);
            tv_jiancai.setTextColor(mResources.getColor(R.color.txt_black));
          }
          tv_zhuangxiu.setTextColor(mResources.getColor(R.color.txt_red));
          break;
        case 2:
          if (mCurrIndex == 0) {
            animation = new TranslateAnimation(0, mPosition_two, 0, 0);
            tv_tuijian.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 1) {
            animation = new TranslateAnimation(mPosition_one, mPosition_two, 0, 0);
            tv_zhuangxiu.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 3) {
            animation = new TranslateAnimation(mPosition_three, mPosition_two, 0, 0);
            tv_jiancai.setTextColor(mResources.getColor(R.color.txt_black));
          }
          tv_sheji.setTextColor(mResources.getColor(R.color.txt_red));
          break;
        case 3:
          if (mCurrIndex == 0) {
            animation = new TranslateAnimation(0, mPosition_three, 0, 0);
            tv_tuijian.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 1) {
            animation = new TranslateAnimation(mPosition_one, mPosition_three, 0, 0);
            tv_zhuangxiu.setTextColor(mResources.getColor(R.color.txt_black));
          } else if (mCurrIndex == 2) {
            animation = new TranslateAnimation(mPosition_two, mPosition_three, 0, 0);
            tv_sheji.setTextColor(mResources.getColor(R.color.txt_black));
          }
          tv_jiancai.setTextColor(mResources.getColor(R.color.txt_red));
          break;
      }
      mCurrIndex = arg0;
      animation.setFillAfter(true);
      animation.setDuration(300);
      iv_bottom_line.startAnimation(animation);
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
  }

  private class SwitchFragMent implements OnClickListener {
    private int index = 0;

    public SwitchFragMent(int i) {
      index = i;
    }

    @Override
    public void onClick(View v) {
      vp_content.setCurrentItem(index);
    }
  }

  @Override
  public void onClick(View arg0) {
    switch (arg0.getId()) {
      case R.id.rl_search:
        startAcMove(new Intent(mActivity,SearchAc.class));
        break;

      default:
        break;
    }
  }

  @Override
  protected int getAcIndex() {
    return 3;
  }

  @Override
  protected String getAppTitle() {
    return null;
  }

}
