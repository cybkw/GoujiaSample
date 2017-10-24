package com.clw.goujia.gjbk;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.ChooseAdapter;
import com.clw.goujia.bean.LinkMan;
import com.clw.goujia.utils.Tools;

/**
 * 立即预约---选择店铺
 * */
@InjectLayer(R.layout.ac_choose_store)
public class ChooseStoreAc extends BaseAc implements OnClickListener, OnItemClickListener {
  private static final String TAG = "ChooseStore";

  @InjectView
  private TextView tv_title;

  @InjectView
  private TextView tv_next;

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private ListView lv_store;

  private ChooseAdapter mChooseAdapter = null;

  private List<LinkMan> linkMans = new ArrayList<LinkMan>();

  @InjectInit
  private void init() {
    mActivity = ChooseStoreAc.this;

    Tools.setFontStyle(mActivity, tv_next, tv_title);

    lv_store.setOnItemClickListener(this);
    tv_next.setOnClickListener(this);
    ib_back.setOnClickListener(this);

    for (int i = 0; i < 1; i++) {
      LinkMan linkMan = new LinkMan();
      linkMan.setStoreName("ss");
      linkMan.setCheck(false);
      linkMans.add(linkMan);
    }

    mChooseAdapter = new ChooseAdapter(mActivity, linkMans);
    lv_store.setAdapter(mChooseAdapter);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_next:
        for (int i = 0; i < linkMans.size(); i++) {
          if (linkMans.get(i).isCheck()) {
            startAcMove(new Intent(mActivity,BuyAc.class));
            break;
          }else{
            showToast("未选择店铺");
          }
        }
        
        break;
      case R.id.ib_back:
        finishAcMove();
        break;

      default:
        break;
    }
  }

  @Override
  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    if (linkMans.get(position).isCheck()) {
      linkMans.get(position).setCheck(false);
    } else {
      linkMans.get(position).setCheck(true);
    }
    mChooseAdapter.notifyDataSetChanged();

  }

}
