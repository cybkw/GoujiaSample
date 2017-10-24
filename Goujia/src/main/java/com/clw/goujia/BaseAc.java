package com.clw.goujia;

import com.android.pc.ioc.inject.InjectInit;
import com.clw.goujia.ui.AbProgressDialogFragment;
import com.clw.goujia.utils.Tools;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 
 * 基类Activity
 * */
public class BaseAc extends FragmentActivity {
  protected Activity mActivity;

  private static final String TAG = "BaseAc";
  
  /** token*/
  protected String mToken="";

  /** 加载框 */
  private ProgressDialog pd;


  @InjectInit
  private void init() {
    mActivity = this;
  }

  /**
   * Toast提示
   * 
   */
  protected void showToast(String content) {
    Tools.Toast(this, content);
  }

  /**
   * 显示等待窗
   * 
   * @param content
   */
  protected void showPD(String content) {
    if (null == pd) {
      pd = new ProgressDialog(this);
    }

    if (null != content) {
      pd.setMessage(content);
      pd.setCancelable(false);
    }

    if (!pd.isShowing()) {
      pd.show();
    }
  }

  protected void showPD() {
    showPD("正在请求数据..");
  }

  /**
   * 关闭等待窗
   * 
   */
  protected void cancelPD() {
    if (null != pd && pd.isShowing()) {
      pd.cancel();
    }
  }

  /**
   * 跳转activity
   * 
   */
  protected void startAc(Intent intent) {
    startActivity(intent);
    overridePendingTransition(0, 0);
  }

  protected void startAcForRes(Intent intent, int requestCode) {
    startActivityForResult(intent, requestCode);
    overridePendingTransition(R.anim.push_right_in, R.anim.alpha_out);
  }

  /**
   * 跳转activity 平移动画
   * 
   */
  protected void startAcMove(Intent intent) {
    startActivity(intent);
    overridePendingTransition(R.anim.push_right_in, R.anim.alpha_out);

  }

  /** Activity 从底部退出动画 */
  protected void startAcButtom(Intent intent) {
    startActivity(intent);
    overridePendingTransition(R.anim.push_right_in, R.anim.push_bottom_out);
  }
  
  /** Activity 从底部升起动画 */
  protected void startAcButtomIn(Intent intent) {
    startActivity(intent);
    overridePendingTransition(R.anim.push_bottom_in, R.anim.alpha_out);
  }

  /**
   * finish当前activity 缩放动画
   * 
   * @param intent
   */
  protected void finishAcScale() {
    finish();
    overridePendingTransition(0, R.anim.scale_out);
  }

  /**
   * finish当前activity
   * 
   */
  protected void finishCurrentAc() {
    finish();
    overridePendingTransition(R.anim.alpha_in, R.anim.push_right_out);
  }

  /**
   * finish当前activity 移动动画
   * 
   * @author Michael.Zhang 2013-10-31 下午1:56:22
   */
  protected void finishAcMove() {
    finish();
    overridePendingTransition(R.anim.alpha_in, R.anim.push_right_out);
  }

  /**
   * 显示软键盘
   * 
   */
  protected void showSoftInputMethod(View v) {
    if (v != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(v, 0);
    }
  }

  /**
   * 收起软键盘
   * 
   */
  protected void hideSoftInputMethod(View v) {
    if (v != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
  }

  /**
   * 按钮点击
   * 
   * @author ZhangYi 2014-3-26 下午3:22:17
   * @param v
   */
  public void onBtnClick(View v) {

  }

  /**
   * 描述：显示进度框.
   * @param context the context
   * @param indeterminateDrawable 用默认请写0
   * @param message the message
   */
  protected static AbProgressDialogFragment showProgressDialog(Context context,int indeterminateDrawable,String message) {
    FragmentActivity activity = (FragmentActivity)context; 
    AbProgressDialogFragment newFragment = AbProgressDialogFragment.newInstance(indeterminateDrawable,message);
    FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
    newFragment.show(ft, "dialog");
      return newFragment;
    }
  
  /**
   * 描述：移除Fragment.
   * @param context the context
   */
  protected static void removeDialog(final Context context){
    try {
      FragmentActivity activity = (FragmentActivity)context; 
      FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
          // 指定一个系统转场动画   
          ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);  
      Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
      if (prev != null) {
          ft.remove(prev);
      }
      ft.addToBackStack(null);
        ft.commit();
    } catch (Exception e) {
      //可能有Activity已经被销毁的异常
      e.printStackTrace();
    }
  }
}
