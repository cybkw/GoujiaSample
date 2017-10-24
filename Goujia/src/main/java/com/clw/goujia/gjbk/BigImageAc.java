package com.clw.goujia.gjbk;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.ui.HackyViewPager;
import com.clw.goujia.ui.ImageDetailFragment;

/**
 * 详情--->查看大图
 * */
@InjectLayer(R.layout.ac_bigimage)
public class BigImageAc extends BaseAc implements OnClickListener {
  private static final String TAG = "BigImageAc";

  @InjectView
  private ImageButton ib_back;

  private Intent mIntent = null;

  // 页码
  @InjectView
  private TextView tv_pager;
  // 位置标识
  private int pagerPosition;

  // 图片源
  private ArrayList<String> images;

  // 图片名
  @InjectView
  private TextView tv_info;

  @InjectView
  private HackyViewPager hvp_pager;
  
  

  @InjectInit
  private void init() {
    mActivity = BigImageAc.this;
    mIntent = getIntent();
    
    ib_back.setOnClickListener(this);
    
    
    images = mIntent.getExtras().getStringArrayList("images");
    pagerPosition = mIntent.getExtras().getInt("position");
    ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images);
    hvp_pager.setAdapter(mAdapter);

    CharSequence text = getString(R.string.viewpager_indicator, 1, hvp_pager.getAdapter().getCount());
    tv_pager.setText(text);

    // 更新下标
    hvp_pager.setOnPageChangeListener(new OnPageChangeListener() {

      @Override
      public void onPageScrollStateChanged(int arg0) {
      }

      @Override
      public void onPageScrolled(int arg0, float arg1, int arg2) {
      }

      @Override
      public void onPageSelected(int arg0) {
        CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, hvp_pager.getAdapter().getCount());
        tv_pager.setText(text);
      }

    });

    hvp_pager.setCurrentItem(pagerPosition);
  }


  @Override
  public void onClick(View arg0) {
    switch (arg0.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;

      default:
        break;
    }
  }

  private class ImagePagerAdapter extends FragmentStatePagerAdapter {

    public ArrayList<String> fileList;

    public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
      super(fm);
      this.fileList = fileList;
    }

    @Override
    public int getCount() {
      return fileList == null ? 0 : fileList.size();
    }

    @Override
    public Fragment getItem(int position) {
      String url = fileList.get(position).toString();
      return ImageDetailFragment.newInstance(url);
    }

  }
}
