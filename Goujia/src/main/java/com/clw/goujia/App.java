package com.clw.goujia;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.android.pc.ioc.app.Ioc;
import com.clw.goujia.utils.StaticField;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.ImageView;

/**
 * Application
 * */
public class App extends Application {

  /** ImageLoder */
  public static ImageLoader IMAGE_LOADER;
  public static DisplayImageOptions options;
  private ImageLoaderConfiguration config;

  /** 首选项 */
  private static SharedPreferences preferences = null;
  
  public static String AREA="上海";
  
  public static boolean isLogin=false;

  @Override
  public void onCreate() {
    Ioc.getIoc().init(this);

    super.onCreate();

    /* app目录 */
    File file = new File(StaticField.SDCARD_PATH);
    if (!file.exists()) {
      file.mkdirs();
    }
    /* app目录 */
    File file_02 = new File(StaticField.SDCARD_FILE);
    if (!file_02.exists()) {
      file_02.mkdirs();
    }

    /* 图片下载初始化 */
    initImageLoader();
  }

  /**
   * 初始化图片下载
   * 
   * @author bkw
   * 
   */
  private void initImageLoader() {
    File cacheDir = new File(StaticField.SDCARD_IMG_TEMP);
    options = new DisplayImageOptions.Builder().cacheOnDisk(true) // 启用外存缓存
        .cacheInMemory(true) // 启用内存缓存
        .displayer(new FadeInBitmapDisplayer(100)) // 是否图片加载好后渐入的动画时间
        .build();
    config = new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.NORM_PRIORITY)
    // 线程池内加载的数量
        .denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
        // 将保存的时候的URI名称用MD5
        .tasksProcessingOrder(QueueProcessingType.LIFO).diskCache(new UnlimitedDiscCache(cacheDir)) // 自定义磁盘缓存路径
        // .threadPoolSize(5) //线程数
        // .diskCacheFileCount(300) //可以设定缓存图片的个数，当超过设定值，删除掉最先加入到硬盘的文件
        .writeDebugLogs().build();
    IMAGE_LOADER = ImageLoader.getInstance();
    IMAGE_LOADER.init(config);
  }

  /**
   * 下载图片
   * 
   * @author bkw
   * 
   * @param uri
   * @param imageView
   */
  public static void LoadImage(String uri, final ImageView imageView) {
    IMAGE_LOADER.displayImage(uri, imageView, options);
  }

  /** 取出本地存取的token */
  public static String getShareToken(Context mContext) {
    preferences = mContext.getSharedPreferences("userInfo", 0);
    String token = preferences.getString("token", "");
    return token;

  }

  /**
   * 清空本地的用户信息
   * */
  public static void clearSharePreferencesInfo(Context mContext) {
    preferences = mContext.getSharedPreferences("userInfo", 0);
    Editor ed = preferences.edit();
    ed.clear();
    ed.putString("username", "");
    ed.putString("token", "");
    ed.putString("user_id", "");
    ed.commit();
  }
  
  private static List<Activity> activityList = new LinkedList<Activity>();

  //添加Activity 到容器中
 public static void addActivity(Activity activity) {
   activityList.add(activity);
 }
  public static void exit(int code) {

    for (Activity activity : activityList) {
      activity.finish();
      activity.overridePendingTransition(0, R.anim.scale_out);
    }

    System.exit(code);

  }
}
