package com.clw.goujia.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class WelcomeAdapter extends PagerAdapter {

	private int[] loading_img_ids;
	private LayoutParams mLayoutParams;
	private Activity activity;

	public WelcomeAdapter(Activity activity, int[] loading_img_ids) {
		this.loading_img_ids = loading_img_ids;
		this.activity = activity;
		mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	@Override
	public int getCount() {
		return loading_img_ids.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv = new ImageView(activity);
		iv.setLayoutParams(mLayoutParams);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setImageResource(loading_img_ids[position]);
		container.addView(iv);
		return iv;
	}
}
