package com.clw.goujia.ui;



import com.clw.goujia.App;
import com.clw.goujia.R;
import com.clw.goujia.utils.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * ImageView创建工厂
 */
public class ViewFactory {

  /**
   * 获取ImageView视图的同时加载显示url
   * 
   * @param text
   * @return
   */
  public static View getContentView(Context context,String imgUrl, int url, String text) {
    View contentView = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
    ImageView iv_banner = (ImageView) contentView.findViewById(R.id.iv_banner);

    if (!Tools.isNull(text)) {
      TextView tv_name = (TextView) contentView.findViewById(R.id.tv_name);
      tv_name.setText(text);
    }
    
    
//    App.LoadImage(url, iv_banner);
    if (!Tools.isNull(imgUrl)) {
      App.LoadImage(imgUrl, iv_banner);
    }else{
      iv_banner.setImageResource(url);
      }
    return contentView;
  }
}
