package com.clw.goujia.home;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectResume;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseViewAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.GjbkAdapter;
import com.clw.goujia.adapter.MenuAdpater;
import com.clw.goujia.cases.ShiGongAc;
import com.clw.goujia.cases.ZhuangXiuAc;
import com.clw.goujia.community.CommunityAc;
import com.clw.goujia.gjbk.GjbkDetails;
import com.clw.goujia.gjbk.GjbkListAc;
import com.clw.goujia.ui.CycleViewPager;
import com.clw.goujia.ui.MyListView;
import com.clw.goujia.ui.CycleViewPager.ImageCycleViewListener;
import com.clw.goujia.ui.NLPullRefreshView;
import com.clw.goujia.ui.NLPullRefreshView.RefreshListener;
import com.clw.goujia.ui.ViewFactory;
import com.clw.goujia.utils.StaticField;
import com.clw.goujia.utils.Tools;

/**
 * 首页
 * */
@TargetApi(Build.VERSION_CODES.HONEYCOMB) @InjectLayer(value = R.layout.ac_home, parent = R.id.fl_content)
public class HomeAc extends BaseViewAc implements OnClickListener, RefreshListener {
  private static final String TAG = "HomeAc";

  @InjectView
  private TextView tv_title;
  
  private Intent mIntent=null;

  // 定位地区
  @InjectView
  private TextView tv_area;

  /** 下拉刷新scorllView */
  @InjectView
  private NLPullRefreshView nlp_refresh;

  // 菜单
  @InjectView
  private GridView gv_menu;

  private String[] menu_name = new String[] { "爆款", "施工", "装修", "社区", "客服" };

  private int[] menu_img = new int[] { R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4,
      R.drawable.icon5 };

  /** 菜单适配器 */
  private MenuAdpater mMenuAdpater = null;

  /** 轮播图 */
  private CycleViewPager cycle_pager;
  /** 滚动图视图列表 */
  private List<View> mViews = new ArrayList<View>();

  /** 构家爆款 */
  @InjectView
  private MyListView mlv_gjbk;

  /** 适配器 */
  private GjbkAdapter mGjbkAdapter = null;

  private int[] mAdinfos = new int[] { R.drawable.image_03, R.drawable.image_06 };

  @InjectInit
  private void init() {
    mActivity = HomeAc.this;

    
    App.addActivity(mActivity);
    tv_area.setText("上海市");
    tv_title.setText("杭州新天地");
    Tools.setFontStyle(mActivity, tv_area, tv_title);

    cycle_pager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.cycle_pager);
    setCycleViews();
    tv_area.setOnClickListener(this);

    gv_menu.setOnItemClickListener(new MenuItemClick());
    mlv_gjbk.setOnItemClickListener(new GJBKItemClick());

    mMenuAdpater = new MenuAdpater(mActivity, menu_img, menu_name);
    gv_menu.setAdapter(mMenuAdpater);
    // 构家爆款
    List<String> list = new ArrayList<String>();
    for (int i = 0; i < 5; i++) {
      list.add("哈哈");
    }
    mGjbkAdapter = new GjbkAdapter(mActivity, list);
    mlv_gjbk.setAdapter(mGjbkAdapter);

    nlp_refresh.setRefreshListener(this);
  }

  @Override
  public void onRefresh(NLPullRefreshView view) {
    refreshHandler.sendEmptyMessageDelayed(1, 2000);
  }

  /**
   * @category 点击事件
   * */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_area:
        startAcForRes(new Intent(mActivity, SwitchCityAc.class).putExtra("area", "上海"), 1);
        break;
      default:
        break;
    }
  }

  /** @category 爆款 */
  private class GJBKItemClick implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
      startAcMove(new Intent(mActivity, GjbkDetails.class));
    }

  }

  /**
   * @category Menu菜单点击事件
   * */
  private class MenuItemClick implements OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
      String type = menu_name[arg2].toString();
   /*   switch (type) {
        case "爆款":
          startAcMove(new Intent(mActivity, GjbkListAc.class));
          break;
        case "施工":
          startAcMove(new Intent(mActivity, ShiGongAc.class));
          break;
        case "社区":
          startAcMove(new Intent(mActivity,CommunityAc.class));
          break;
        case "装修":
          startAcMove(new Intent(mActivity,ZhuangXiuAc.class));
          break;
        case "客服":
          String phone = "100188";
          mIntent = new Intent();
          mIntent.setAction(Intent.ACTION_DIAL);
          mIntent.setData(Uri.parse("tel:" + phone));
          startActivity(mIntent);
          break;
        default:
          break;
      }*/
    }

  }

  /**
   * @category 滚动图配置
   * */
  private void setCycleViews() {
    if (mAdinfos.length > 0) {
      mViews.add(ViewFactory.getContentView(mActivity,"", mAdinfos[mAdinfos.length - 1], ""));
      for (int i = 0; i < mAdinfos.length; i++) {
        mViews.add(ViewFactory.getContentView(this,"", mAdinfos[i], ""));
      }

      // 将第一个ImageView添加进来
      mViews.add(ViewFactory.getContentView(this,"", mAdinfos[0], ""));
      // 设置循环，在调用setData方法前调用
      cycle_pager.setCycle(true);
      // 在加载数据前设置是否循环
      cycle_pager.setData(mViews, mAdinfos, mAdCycleViewListener);
      // 设置轮播
      cycle_pager.setWheel(true);
      cycle_pager.setShowPager(false);
      // 设置轮播时间，默认5000ms
      cycle_pager.setTime(5000);
    }
  }

  /**
   * @category 轮播图点击事件
   * */
  private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
    @Override
    public void onImageClick(int info, int position, View imageView) {
      if (cycle_pager.isCycle()) {
        position = position - 1;
      }
    }
  };

  /**
   * 刷新handler
   * */
  private Handler refreshHandler = new Handler() {
    public void handleMessage(android.os.Message msg) {
      nlp_refresh.finishRefresh();
      showToast("刷新成功");
    };
  };


  @InjectResume
  protected void onResume() {
    super.onResume();
    tv_area.setText(App.AREA);
  };

  @Override
  protected int getAcIndex() {
    return 1;
  }

  @Override
  protected String getAppTitle() {
    return null;
  }

}
