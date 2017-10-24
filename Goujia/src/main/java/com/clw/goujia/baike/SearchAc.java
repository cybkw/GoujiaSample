package com.clw.goujia.baike;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

/**
 * 百科--->搜索
 * */
@InjectLayer(R.layout.ac_search)
public class SearchAc extends BaseAc implements OnClickListener{
  private static final String TAG = "SearchAc";
  
  @InjectView
  private EditText et_keyword;
  
  @InjectView 
  private ListView lv_article;
  
  @InjectView
  private Button btn_cancle;
  
  @InjectInit
  private void init(){
    mActivity=SearchAc.this;
    
    
    Tools.setETFontStyle(mActivity, et_keyword);
    Tools.setBTNFontStyle(mActivity, btn_cancle);
    btn_cancle.setOnClickListener(this);
    et_keyword.requestFocus();
    et_keyword.setOnEditorActionListener(new SearchAction());
  }
  
  
  private class SearchAction implements OnEditorActionListener{
    @Override
    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
      if (arg1==EditorInfo.IME_ACTION_SEARCH) {
        showToast("未搜索到当前关键字");
      }
      return false;
    }}

  @Override
  public void onClick(View arg0) {
    switch (arg0.getId()) {
      case R.id.btn_cancle:
        finishAcMove();
        break;

      default:
        break;
    }
  }
}
