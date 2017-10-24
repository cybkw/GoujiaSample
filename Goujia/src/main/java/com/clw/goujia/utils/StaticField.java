package com.clw.goujia.utils;

import android.os.Environment;

/**
 * 常量公共类
 * */
public class StaticField {
  @SuppressWarnings("unused")
  private static final String TAG = "StaticField";

  /** 屏幕大小 */
  public static int SCREEN_HEIGHT = 800;
  public static int SCREEN_WIDHT = 480;

  /** 字体 */
  public static final String JIANTI="fonts/fzltxhjw.ttf";

  /** sdcard 路径 */
  public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath() + "/Goujia";
  public static final String SDCARD_IMG_TEMP = SDCARD_PATH + "/cache/";
  public static final String SDCARD_FILE = SDCARD_PATH + "/files/";
  
  /** token值 */
  public static String TOKEN = "";
  
  public static boolean Is_Login=false;



}
