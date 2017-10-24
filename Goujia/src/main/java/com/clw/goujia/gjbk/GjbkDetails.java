package com.clw.goujia.gjbk;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.BkDetailsAdapter;
import com.clw.goujia.bean.ClassIc;
import com.clw.goujia.bean.Product;
import com.clw.goujia.ui.CycleViewPager;
import com.clw.goujia.ui.ViewFactory;
import com.clw.goujia.ui.CycleViewPager.ImageCycleViewListener;
import com.clw.goujia.utils.Tools;

/**
 * 产品包详情
 * */
@InjectLayer(R.layout.ac_gjbk_details)
public class GjbkDetails extends BaseAc implements OnClickListener {
  private static final String TAG = "GjbkDetails";

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private TextView tv_title;

  // vr
  @InjectView
  private ImageButton ib_vr;

  // 轮播图
  private CycleViewPager cyp_image;

  @InjectView
  private ExpandableListView elv_details;

  /** 滚动图视图列表 */
  private List<View> mViews = new ArrayList<View>();

  private int[] mAdinfos = new int[] { R.drawable.image_03 };
  private String[] names = new String[] { "客厅" };

  private String[] imgUrl = new String[] { "http://source.cloudin9.com/xintd/img/cover.jpg?ImageView2/2/w/1280" };

  /** 父级分类 */
  private List<ClassIc> mClassIcs = new ArrayList<ClassIc>();

  /** 子分类 */
  private List<List<Product>> mProducts = new ArrayList<List<Product>>();

  private BkDetailsAdapter mBkDetailsAdapter = null;

  @InjectView
  private TextView tv_total;

  @InjectView
  private Button btn_sumbit;

  private List<Product> mChilds = new ArrayList<Product>();

  private ArrayList<String> images = new ArrayList<String>();

  @InjectInit
  private void init() {
    mActivity = GjbkDetails.this;

    Tools.setFontStyle(mActivity, tv_title, tv_total);
    ib_back.setOnClickListener(this);
    btn_sumbit.setOnClickListener(this);
    ib_vr.setOnClickListener(this);

    images.add("http://source.cloudin9.com/xintd/img/cover.jpg?ImageView2/2/w/1280/h/800");

    // 屏蔽Group点击事件
    elv_details.setOnGroupClickListener(new OnGroupClickListener() {
      @Override
      public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
      }
    });

    elv_details.setOnChildClickListener(new OnChildClickListener() {

      @Override
      public boolean onChildClick(ExpandableListView arg0, View arg1, int groupPosition, int childPosition, long arg4) {
        // showToast(mProducts.get(groupPosition).get(childPosition).getBrand());
        if (mProducts.get(groupPosition).get(childPosition).isCheck()) {
          mProducts.get(groupPosition).get(childPosition).setCheck(false);
          if (mChilds.size() > 0) {
            for (int i = 0; i < mChilds.size(); i++) {
              if (mChilds.get(i).get_id().equals(mProducts.get(groupPosition).get(childPosition).get_id())) {
                mChilds.remove(i);
                i--;
              }
            }
            getTotal();
          }
        } else {
          mProducts.get(groupPosition).get(childPosition).setCheck(true);
          Product product = new Product();
          product.set_id(mProducts.get(groupPosition).get(childPosition).get_id());
          product.setBrand(mProducts.get(groupPosition).get(childPosition).getBrand());
          product.setColor(mProducts.get(groupPosition).get(childPosition).getColor());
          product.setMaterial(mProducts.get(groupPosition).get(childPosition).getMaterial());
          product.setPrice(mProducts.get(groupPosition).get(childPosition).getPrice());
          product.setSize(mProducts.get(groupPosition).get(childPosition).getSize());
          mChilds.add(product);
          getTotal();
        }
        mBkDetailsAdapter.notifyDataSetChanged();
        return false;
      }
    });

    cyp_image = (CycleViewPager) getFragmentManager().findFragmentById(R.id.cyp_image);
    setCycleViews();

    ClassIc classIc = new ClassIc();
    classIc.setClassic("地板");
    ClassIc classIc2 = new ClassIc();
    classIc2.setClassic("门框");
    mClassIcs.add(classIc);
    mClassIcs.add(classIc2);

    List<Product> childs = new ArrayList<Product>();
    for (int i = 0; i < 2; i++) {
      Product child = new Product();
      child.set_id("" + i);
      child.setBrand("雅琪诺");
      child.setColor("暖色");
      child.setMaterial("木头");
      child.setPrice(102);
      child.setSize("0.53*10m");
      child.setCheck(false);
      childs.add(child);
    }

    for (int index = 0; index < mClassIcs.size(); ++index) {
      mProducts.add(childs);
    }

    mBkDetailsAdapter = new BkDetailsAdapter(mActivity, mClassIcs, mProducts);
    elv_details.setAdapter(mBkDetailsAdapter);

    for (int i = 0; i < mBkDetailsAdapter.getGroupCount(); i++) {
      elv_details.expandGroup(i);
    }

  }

  /**
   * @category 滚动图配置
   * */
  private void setCycleViews() {
    if (mAdinfos.length > 0) {

      mViews.add(ViewFactory.getContentView(mActivity, imgUrl[imgUrl.length - 1], 0, names[names.length - 1]));
      // for (int i = 0; i < mAdinfos.length; i++) {
      mViews.add(ViewFactory.getContentView(this, imgUrl[0], 0, names[0]));
      // }
      // 将第一个ImageView添加进来
      mViews.add(ViewFactory.getContentView(this, imgUrl[0], 0, names[0]));
      // 设置循环，在调用setData方法前调用
      cyp_image.setCycle(true);
      // 在加载数据前设置是否循环
      cyp_image.setData(mViews, mAdinfos, mAdCycleViewListener);
      // 设置轮播
      cyp_image.setWheel(true);
      cyp_image.setShowIndicator(false);
      cyp_image.setShowPager(true);
      // 设置轮播时间，默认5000ms
      cyp_image.setTime(5000);
    }
  }

  /**
   * @category 轮播图点击事件
   * */
  private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
    @Override
    public void onImageClick(int info, int position, View imageView) {
      if (cyp_image.isCycle()) {
        position = position - 1;
        startAcMove(new Intent(mActivity, BigImageAc.class).putExtra("images", images).putExtra("position", position));
      }
    }
  };

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;
      case R.id.btn_sumbit:
        if (mChilds.size() > 0) {
          startAcMove(new Intent(mActivity, ChooseStoreAc.class));
        } else {
          showToast("请选择一件商品");
        }

        break;
      case R.id.ib_vr:
//        String url = "http://source.cloudin9.com/xintd/nvr/index.html";
        String url="http://source.cloudin9.com/xintd/ovr/index.html";
//        String url="https://aframe.io/examples/";
//        String url="http://cdn.goujiawang.com/store/files/2016/05/1462787433671/index.html";
        // 包名 :com.android.chrome
        openCLD("com.android.chrome", mActivity, url);
        break;
      default:
        break;
    }
  }

  /**
   * @category 计算总价
   * */
  public void getTotal() {
    int sumprice = 0;
    for (int i = 0; i < mChilds.size(); i++) {
      sumprice = (int) (mChilds.size() * mChilds.get(i).getPrice());
    }

    tv_total.setText("共" + mChilds.size() + "项,总计:￥" + sumprice + "元");
  }

  /**
   * @category 启动谷歌浏览器打开页面
   * */
  private void openCLD(String packageName, Context context, String url) {
    PackageManager packageManager = context.getPackageManager();
    PackageInfo pi = null;
    try {

      pi = packageManager.getPackageInfo(packageName, 0);
      Log.i(TAG, "pi.package" + pi.packageName);
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    resolveIntent.setPackage(pi.packageName);

    List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent, 0);

    ResolveInfo ri = apps.iterator().next();
    if (ri != null) {
      String className = ri.activityInfo.name;

      Intent intent = new Intent(Intent.ACTION_MAIN);
      intent.addCategory(Intent.CATEGORY_LAUNCHER);
      intent.setData(Uri.parse(url));
      ComponentName cn = new ComponentName(packageName, className);

      intent.setComponent(cn);
      context.startActivity(intent);
    }
  }
}
