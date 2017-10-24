package com.clw.goujia.gjbk;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
@InjectLayer(R.layout.ac_bk_vr)
public class BkVRAc extends BaseAc implements OnClickListener{
  private static final String TAG = "BkVRAc";
  
  @InjectView 
  private ImageButton ib_back;
  
  @InjectView
  private TextView tv_title;
  
  @InjectView 
  private WebView wv_content;
  
  @InjectInit
  private void init(){
    mActivity=BkVRAc.this;
        
    ib_back.setOnClickListener(this);
    
    WebSettings webSettings = wv_content.getSettings();
    webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); // 就是这句
    webSettings.setJavaScriptEnabled(true);
//    webSettings.setBuiltInZoomControls(true);
    webSettings.setDomStorageEnabled(true);
//    wv_content.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    
    String url="http://source.cloudin9.com/xintd/nvr/index.html";
    
    wv_content.loadUrl(url);
//    wv_contents.loadDataWithBaseURL(null, pdb.getShowContent(), "text/html", "utf-8", null);
//    wv_content.loadDataWithBaseURL(url, "", "text/html", "utf-8", null);
    
    
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
