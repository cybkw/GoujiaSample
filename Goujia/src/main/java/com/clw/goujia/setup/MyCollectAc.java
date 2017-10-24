package com.clw.goujia.setup;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.CollectAdapter;
import com.clw.goujia.bean.Product;
import com.clw.goujia.ui.XListView;
import com.clw.goujia.utils.Tools;

/**
 * 收藏
 * */
@InjectLayer(R.layout.ac_my_collect)
public class MyCollectAc extends BaseAc implements OnClickListener {
  private static final String TAG = "MyCollectAc";

  @InjectView
  private XListView xlv_collect;

  @InjectView
  private TextView tv_title;

  @InjectView
  private ImageButton ib_back;

  private CollectAdapter mCollectAdapter = null;

  private List<Product> mProducts = new ArrayList<Product>();

  @InjectInit
  private void init() {
    mActivity = MyCollectAc.this;

    Tools.setFontStyle(mActivity, tv_title);
    ib_back.setOnClickListener(this);
    xlv_collect.setPullLoadEnable(false);
    xlv_collect.setPullRefreshEnable(false);

    mCollectAdapter = new CollectAdapter(mActivity, mProducts);
    xlv_collect.setAdapter(mCollectAdapter);
    for (int i = 0; i < 2; i++) {
      Product product = new Product();
      product.set_id(i + "");
      mProducts.add(product);
    }
    mCollectAdapter.notifyDataSetChanged();
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
