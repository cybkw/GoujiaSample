package com.clw.goujia.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.clw.goujia.R;
import com.clw.goujia.ui.XListView;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.os.RemoteException;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

/**
 * 工具类
 * 
 * @author bkw
 * 
 */
public class Tools {

  private static final String TAG = "Tools";

  private static TelephonyManager telephonyManager;

  /**
   * @category webview 设置html文本
   * 
   * @param webview
   * @param content
   */
  public static void setWebView(final WebView webview, String content) {
    // webview.getSettings().setBlockNetworkImage(true);
    // webview.getSettings().setUseWideViewPort(false);
    // webview.getSettings()
    // .setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
    webview.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageFinished(WebView view, String url) {
        webview.getSettings().setBlockNetworkImage(false);
      }
    });
    webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    webview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    webview.setBackgroundColor(Color.parseColor("#00000000"));
    webview.setBackgroundResource(R.color.white);
  }

  /**
   * @category 根据年 月 获取对应的月份 天数
   * */
  public static int getDaysByYearMonth(int year, int month) {
    Calendar a = Calendar.getInstance();
    a.set(Calendar.YEAR, year);
    a.set(Calendar.MONTH, month - 1);
    a.set(Calendar.DATE, 1);
    a.roll(Calendar.DATE, -1);
    int daynum = a.get(Calendar.DATE);
    return daynum;
  }

  /**
   * @category 将dp类型的尺寸转换成px类型的尺寸
   * 
   * @param size
   * @param context
   * @return
   */
  public static int dip2px(Context context, int dipValue) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  /**
   * 获取加密签名
   * 
   * @param timestamp
   * @return
   */
  // public static String getSign(String timestamp) {
  // String sign = "";
  // String key = timestamp + StaticField.VERSION + StaticField.SECURITY_KEY;
  // sign = Tools.MD5(key.toLowerCase());
  // return sign;
  // }

  /**
   * @category MD5加密
   * 
   * @param plainText
   * @return
   */
  public static String MD5(String plainText) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(plainText.getBytes());
      byte b[] = md.digest();
      int i;
      StringBuffer buf = new StringBuffer("");
      for (int offset = 0; offset < b.length; offset++) {
        i = b[offset];
        if (i < 0) {
          i += 256;
        }

        if (i < 16) {
          buf.append("0");
        }

        buf.append(Integer.toHexString(i));
      }

      return buf.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * @category 获取手机唯一设备号
   */
  public static String getDeviceId(Activity activity) {
    String imei = "";
    telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
    imei = telephonyManager.getDeviceId();
    return imei;
  }

  /**
   * @category 根据时间戳返回时分秒 --- 倒计时
   * 
   * @param time
   * @return
   */
  public static String getMSTime(long time1) {
    long time = time1 / 1000;
    long a, b, c = 0;
    a = time % 60;
    b = time / 60;
    if (b > 60) {
      c = b / 60;
      b = b % 60;
    }
    return c + ":" + b + ":" + a;
  }

  /**
   * @category 判断当前是够有网络
   * 
   * @param context
   * @return
   */
  public static boolean checkIsOnLine(Context context) {
    ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (connectivity != null) {
      // 获取网络连接管理的对象
      NetworkInfo info = connectivity.getActiveNetworkInfo();
      if (info != null) {
        // 判断当前网络是否已经连接
        if (info.isConnected()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @category 将Bitmap转换base64加密的字符串
   */
  public static String bitmaptoString(Bitmap bitmap) {
    String string = null;
    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
    bitmap.compress(CompressFormat.PNG, 100, bStream);
    byte[] bytes = bStream.toByteArray();
    string = Base64.encodeToString(bytes, Base64.DEFAULT);
    return string;
  }

  /** @category 获取字符串 */
  public static String getString(Context context, int stringid) {
    String content;
    if (context == null || stringid == 0) {
      content = "error";
    } else {
      content = context.getString(stringid);
    }
    return content;
  }

  /** @category 把字符串转成数字 */
  public static int stringToInt(String num) {
    if (isNull(num)) {
      num = "0";
    }
    return Integer.parseInt(num);
  }

  /**
   * 地图初始位置定位
   */
  @SuppressWarnings("unused")
  private static double LONGITUDE = 0;
  @SuppressWarnings("unused")
  private static double LATITUDE = 0;

  // // 存入当前定位城市
  // public static void setCurrLocationCity(Context c, String city) {
  // SharedPreferences sp = c.getSharedPreferences("curr_location_city",
  // Context.MODE_PRIVATE);
  // sp.edit().putString("city", city).commit();
  // }
  //
  // // 取出当前定位城市
  // public static String getCurrLocationCity(Context c) {
  // String str = "";
  // SharedPreferences sp = c.getSharedPreferences("curr_location_city",
  // Context.MODE_PRIVATE);
  // if (sp != null) {
  // str = sp.getString("city", "");
  // }
  // return str;
  // }
  //
  // // 存入当前选择城市
  // public static void setCurrChoisedCity(Context c, String city) {
  // SharedPreferences sp = c.getSharedPreferences("curr_choised_city",
  // Context.MODE_PRIVATE);
  // sp.edit().putString("city", city).commit();
  // }
  //
  // // 取出当前选择城市
  // public static String getCurrChoisedCity(Context c) {
  // String str = "";
  // SharedPreferences sp = c.getSharedPreferences("curr_choised_city",
  // Context.MODE_PRIVATE);
  // if (sp != null) {
  // str = sp.getString("city", "");
  // }
  // return str;
  // }

  // public static double getLongitude(Context c) {// 经度
  // if (LONGITUDE == 0) {
  // SharedPreferences sp = c.getSharedPreferences("longitude",
  // Context.MODE_PRIVATE);
  // LONGITUDE = Double.parseDouble(sp.getString("num", "-1"));
  // }
  // return LONGITUDE;
  // }
  //
  // public static double getLatitude(Context c) {// 纬度
  // if (LATITUDE == 0) {
  // SharedPreferences sp = c.getSharedPreferences("latitude",
  // Context.MODE_PRIVATE);
  // LATITUDE = Double.parseDouble(sp.getString("num", "-1"));
  // }
  // return LATITUDE;
  // }
  //
  // public static void setLongitude(Context c, double longitude) {
  // LONGITUDE = longitude;
  // SharedPreferences sp = c.getSharedPreferences("longitude",
  // Context.MODE_PRIVATE);
  // sp.edit().putString("num", String.valueOf(longitude)).commit();
  // }
  //
  // public static void setLatitude(Context c, double latitude) {
  // LATITUDE = latitude;
  // SharedPreferences sp = c.getSharedPreferences("latitude",
  // Context.MODE_PRIVATE);
  // sp.edit().putString("num", String.valueOf(latitude)).commit();
  // }

  /**
   * @category 获取屏幕信息 宽,高
   * 
   * @author Michael.Zhang 2013-10-31 下午5:16:01
   */
  public static void getScreenWidth(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    StaticField.SCREEN_HEIGHT = dm.heightPixels;
    StaticField.SCREEN_WIDHT = dm.widthPixels;
  }

  public static int[] getScreenSize(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return new int[] { dm.widthPixels, dm.heightPixels };
  }

  /**
   * @category 压缩图片
   * 
   * @param bm
   * @param newWidth
   * @param newHeight
   * @return
   */
  public static Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
    // 获得图片的宽高
    int width = bm.getWidth();
    int height = bm.getHeight();
    // 设置想要的大小
    int newWidth1 = newWidth;
    int newHeight1 = newHeight;
    // 计算缩放比例
    float scaleWidth = ((float) newWidth1) / width;
    float scaleHeight = ((float) newHeight1) / height;
    // 取得想要缩放的matrix参数
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);
    matrix.postRotate(0);

    return Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
  }

  /**
   * @category 存图片到sdcard
   * 
   * @author Michael.Zhang
   * @param bitmap1
   */
  public static void storeInSD(Bitmap bitmap, String img_name) {
    File file = new File(StaticField.SDCARD_PATH);
    File imageFile = new File(file, img_name);
    try {
      imageFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(imageFile);
      bitmap.compress(CompressFormat.JPEG, 80, fos);
      fos.flush();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @category 将图片存入sd卡中缓存目录
   * 
   * @param bitmap
   *          图片
   * @param img_name
   *          图片名字
   */
  public static String cacheImgToSd(Bitmap bitmap, String img_name) {
    File file = new File(StaticField.SDCARD_IMG_TEMP);
    if (!file.exists()) {
      file.mkdirs();
    }
    File imageFile = new File(file, img_name);
    try {
      imageFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(imageFile);
      bitmap.compress(CompressFormat.JPEG, 80, fos);
      fos.flush();
      fos.close();
      return imageFile.getAbsolutePath();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }

  }

  public static void Log(Object s) {
    if (s == null) {
      s = "传进来的是null";
    }

    Log.i("info", s.toString());
  }

  public static void Toast(Context context, String s) {
    if (context != null && s != null) {
      android.widget.Toast.makeText(context, s, android.widget.Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * @category 判断 多个字段的值否为空
   * 
   * @return true为null或空; false不null或空
   */
  public static boolean isNull(String... ss) {
    for (int i = 0; i < ss.length; i++) {
      if (null == ss[i] || ss[i].equals("") || ss[i].equalsIgnoreCase("null")) {
        return true;
      }
    }

    return false;
  }

  /**
   * @category 判断 一个字段的值否为空
   * 
   * @param s
   * @return
   */
  public static boolean isNull(String s) {
    if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
      return true;
    }

    return false;
  }

  /**
   * @category 判断两个字段是否一样
   * 
   */
  public static boolean judgeStringEquals(String s0, String s1) {
    return s0 != null && null != s1 && s0.equals(s1);
  }

  /**
   * @category 根据给定的格式化参数，将给定的字符串时间转换为需要的字符串
   * 
   * @param dateString
   * @param dateFormat
   * @return java.util.Date
   */
  public static String parse(String dateString, String dateFormat) {
    if ("".equals(dateString.trim()) || dateString == null) {
      return null;
    }
    long da = Long.parseLong(dateString);
    DateFormat sdf = new SimpleDateFormat(dateFormat);
    Date date = new Date(da);

    return sdf.format(date);
  }

  /**
   * 
   * @category 将时间戳转为字符串 到秒
   * 
   * 
   * @param cc_time
   * @return
   */
  public static String getStrTime(String cc_time) {
    String re_StrTime = null;
    if (cc_time == null) {
      cc_time = System.currentTimeMillis() + "";
    }

    if (cc_time.length() == 10) { // 单位 秒
      cc_time += "000";
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long lcc_time = Long.valueOf(cc_time);
    re_StrTime = sdf.format(new Date(lcc_time));

    return re_StrTime;

  }

  /**
   * 
   * @category 将时间戳转为字符串 到日
   * 
   * 
   * @param cc_time
   * @return
   */
  public static String getStrDate(String cc_time) {
    String re_StrTime = "";
    if (cc_time == null) {
      cc_time = System.currentTimeMillis() + "";
    }

    if (cc_time.length() == 10) {
      cc_time += "000";
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    long lcc_time = Long.valueOf(cc_time);
    re_StrTime = sdf.format(new Date(lcc_time));

    return re_StrTime;
  }

  /**
   * @category 将字符串到分 转换为时间戳
   * 
   * @param user_time
   * @return
   */
  public static String getTime(String user_time) {
    String re_time = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date d;
    try {
      d = sdf.parse(user_time);
      long l = d.getTime();
      String str = String.valueOf(l);
      re_time = str.substring(0, 13);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return re_time;
  }

  /**
   * @category 将字符串到日 转化为时间戳
   * 
   * @param time
   * @return
   */
  public static String getTimeMillisByDate(String time) {
    String re_time = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d;
    try {
      d = sdf.parse(time);
      long l = d.getTime();
      String str = String.valueOf(l);
      re_time = str.substring(0, 10);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return re_time;
  }

  /**
   * @category 获取空格前面的字符串
   * 
   * 
   * @param time
   * @return
   */
  public static String getDate(String time) {
    String date = "";
    date = time.substring(0, time.indexOf(' '));
    return date;
  }

  /**
   * @category 获取当前系统时间
   * 
   */
  public static String getCurrentTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    Date curDate = new Date(System.currentTimeMillis());
    String str = formatter.format(curDate);

    return str;
  }

  /**
   * @category 计算两个时间差，换算出分钟数量)
   * @Title: getCutMinute
   * @param time1
   *          小的时间
   * @param time2
   *          大的时间
   * @date 2014-6-10 上午11:55:09
   * @return long 返回类型
   */
  public static long getCutMinute(String time1, String time2) {
    // 这个是在创建足迹的时候，调用的getCurrentTime方法里面，给date设置的format格式，这里通过这个相同的格式将时间转换回date格式
    String formateString = "yyyy-MM-dd HH:mm:ss ";

    SimpleDateFormat formatter = new SimpleDateFormat(formateString);
    long min = 0;

    try {
      Date date1 = formatter.parse(time1);
      Date date2 = formatter.parse(time2);

      long l = date2.getTime() - date1.getTime();
      min = l / (1000 * 60);
    } catch (ParseException e) {
    }

    return min;
  }

  // 计算两点距离
  public static final double EARTH_RADIUS = 6378137.0;

  /**
   * @Title: gps2m
   * @Description: TODO(根据两个经纬度，计算两点之间的距离，得到的单位是米)
   * @author windy
   * @date 2014-6-10 下午12:02:53
   * @return double 返回类型
   */
  public static double getMileFromTwoLocation(double lat1, double lng1, double lat2, double lng2) {
    double radLat1 = (lat1 * Math.PI / 180.0);
    double radLat2 = (lat2 * Math.PI / 180.0);
    double a = radLat1 - radLat2;
    double b = (lng1 - lng2) * Math.PI / 180.0;
    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
        * Math.pow(Math.sin(b / 2), 2)));
    s = s * EARTH_RADIUS;
    s = Math.round(s * 10000) / 10000;
    return s;
  }

  /**
   * @category 将分转为元
   * 
   * @return
   */
  public static double getMoney(String money) {
    if (money != null && !money.equals("") && !money.equals("null")) {
      return Double.parseDouble(money) / 100.0;
    }

    return 0.00;
  }

  /**
   * @category 验证身份证号码
   * 
   * @param idCard
   * @return
   */
  public static boolean isIdCard(String idCard) {
    if (isNull(idCard))
      return false;
    String pattern = "^[0-9]{17}[0-9|xX]{1}$";
    return idCard.matches(pattern);
  }

  /**
   * @category 验证手机号码
   * 
   * @param phone
   * @return
   */
  public static boolean isPhone(String phone) {
    if (isNull(phone))
      return false;
    // String pattern =
    // "^((13[0-9])|(147)|(177)|(15[^4,\\D])|(183)|(18[0-9]))\\d{8}$";
    String pattern = "^1\\d{10}";
    return phone.matches(pattern);
  }

  /**
   * @category 判断email格式是否正确
   * 
   * @param email
   * @return
   */
  public static boolean isEmail(String email) {
    String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    Pattern p = Pattern.compile(str);
    Matcher m = p.matcher(email);
    return m.matches();
  }

  /**
   * @category 简单的验证一下银行卡号
   * 
   * @param bankCard
   *          信用卡是16位，其他的是13-19位
   * @return
   */
  public static boolean isBankCard(String bankCard) {
    if (isNull(bankCard))
      return false;
    String pattern = "^\\d{13,19}$";
    return bankCard.matches(pattern);
  }

  /**
   * @category 将px类型的尺寸转换成dp类型的尺寸
   * 
   * @param size
   * @param context
   * @return
   */
  public static int PXtoDP(Context context, int pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * @category 将dp类型的尺寸转换成px类型的尺寸
   * 
   * @param size
   * @param context
   * @return
   */
  public static int DPtoPX(Context context, int dipValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  /**
   * @category 整理
   * 
   * @return
   */
  public static Double roundDouble(double val, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b = ((0 == val) ? new BigDecimal("0.0") : new BigDecimal(Double.toString(val)));
    BigDecimal one = new BigDecimal("1");
    return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * @category 判断 列表是否为空
   * 
   * @return true为null或空; false不null或空
   */
  public static boolean isEmptyList(List<?> list) {
    return list == null || list.size() == 0;
  }

  /**
   * @category 判断sd卡是否存在
   * 
   * @return
   */
  public static boolean judgeSDCard() {
    String status = Environment.getExternalStorageState();
    return status.equals(Environment.MEDIA_MOUNTED);
  }

  /**
   * @category 判断 http 链接
   * 
   * @param url
   * @return
   */
  public static boolean isUrl(String url) {
    return null != url && url.startsWith("http://");
  }

  /**
   * @category 判断图品路径
   * 
   * @return
   */
  public static boolean isImgUrl(String imgUrl) {
    return isUrl(imgUrl) && imgUrl.endsWith(".jpg");
  }

  /**
   * @category 获得hashmap中value的值,以List 返回
   * 
   * @param hashMap
   * @return
   */
  @SuppressWarnings({ "rawtypes", "unused" })
  public static List<String> getListByHashMap(HashMap<String, String> hashMap) {
    List<String> list = new ArrayList<String>();
    Iterator iter = hashMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry entry = (Map.Entry) iter.next();
      Object key = entry.getKey();
      Object val = entry.getValue();
      list.add((String) val);
    }

    return list;
  }

  /**
   * @category 获取版本号 给用户看的
   * 
   * @return
   */
  public static String getVersionName(Activity activity) {
    String versionName = "0";
    if (getPackageInfo(activity) != null) {
      versionName = getPackageInfo(activity).versionName;
    }

    return versionName;
  }

  /**
   * @category 获取版本号 系统识别用的
   * 
   * @return
   */
  public static int getVersionCode(Activity activity) {
    int versionCode = 0;
    if (getPackageInfo(activity) != null) {
      versionCode = getPackageInfo(activity).versionCode;
    }

    return versionCode;
  }

  private static PackageInfo getPackageInfo(Activity activity) {
    String packageName = activity.getPackageName();
    try {
      return activity.getPackageManager().getPackageInfo(packageName, 0);
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * @category 获取保存到View的Tag中的字符串
   * 
   * @param v
   * @return
   */
  public static String getViewTagString(View v) {
    try {
      return v.getTag().toString();
    } catch (Exception e) {
      return "0";
    }
  }

  /**
   * @category 格式化价格 支付宝用
   * 
   * @return
   */
  public static String FormatPrice(double price) {
    DecimalFormat format = new DecimalFormat("0.00");
    String totalprice = format.format(price);
    return totalprice;
  }

  /**
   * @category 判断是否有网络
   * 
   * @param context
   * @return
   */
  public static boolean isNetworkConnected(Context context) {
    ConnectivityManager mConnectivityManager = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
    return (mNetworkInfo != null && mNetworkInfo.isAvailable());
  }

  /**
   * @category 缩放图片布局高度为宽度的4分之3
   * */
  public static void setLayoutWandH(View view, Activity mActivity) {
    LayoutParams lp = (LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = StaticField.SCREEN_WIDHT - (StaticField.SCREEN_WIDHT / 4);
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局1比1
   * 
   * */
  public static void setLayout1to1(View view, Activity mActivity) {
    LayoutParams lp = (LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = StaticField.SCREEN_WIDHT;
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局1比1
   * 
   * */
  public static void setFrameLayout1to1(View view, Activity mActivity) {
    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = StaticField.SCREEN_WIDHT;
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局2比1
   * 
   * */
  public static void setFrameLayout2to1(View view, Activity mActivity) {
    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = StaticField.SCREEN_WIDHT / 2;
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局2比1
   * 
   * */
  public static void setFrameLayout2to1Add(View view, Activity mActivity) {
    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = (StaticField.SCREEN_WIDHT / 2) + 50;
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局2比1
   * 
   * */
  public static void setLayout2to1(View view, Activity mActivity) {
    LayoutParams lp = (LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = StaticField.SCREEN_WIDHT / 2;
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局2比1
   * 
   * */
  public static void setLayout2to1Add(View view, Activity mActivity) {
    LayoutParams lp = (LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = (StaticField.SCREEN_WIDHT / 2) + 50;
    view.setLayoutParams(lp);
  }

  public static void setRelaLayout2to1Add(View view, Activity mActivity) {
    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = (StaticField.SCREEN_WIDHT / 2) + 50;
    view.setLayoutParams(lp);
  }

  /**
   * @category 缩放图片布局3比1
   * 
   * */
  public static void setLayout3to1(View view, Activity mActivity) {
    LayoutParams lp = (LayoutParams) view.getLayoutParams();
    Tools.getScreenWidth(mActivity);
    lp.width = StaticField.SCREEN_WIDHT;
    lp.height = StaticField.SCREEN_WIDHT - (StaticField.SCREEN_WIDHT / 3);
    view.setLayoutParams(lp);
  }

  @SuppressWarnings("unused")
  public static int doImage(String url) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    /**
     * 最关键在此，把options.inJustDecodeBounds = true;
     * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
     */
    options.inJustDecodeBounds = true;
    Bitmap bitmap = BitmapFactory.decodeFile(url, options); // 此时返回的bitmap为null
    Log.i("Test", "Bitmap Height == " + options.outHeight);
    return options.outWidth;
  }

  /**
   * @category 设置字体 传入TextView控件
   * */
  public static void setFontStyle(Context mContext, TextView... tv) {
    Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), StaticField.JIANTI);
    for (int i = 0; i < tv.length; i++) {
      tv[i].setTypeface(typeface);
    }
  }

  /**
   * @category 设置字体 传入Button控件
   * */
  public static void setBTNFontStyle(Context mContext, Button... tv) {
    Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), StaticField.JIANTI);
    for (int i = 0; i < tv.length; i++) {
      tv[i].setTypeface(typeface);
    }
  }

  /**
   * @category 设置字体 传入EditText控件
   * */
  public static void setETFontStyle(Context mContext, EditText... tv) {
    Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), StaticField.JIANTI);
    for (int i = 0; i < tv.length; i++) {
      tv[i].setTypeface(typeface);
    }
  }

  /**
   * @category 判断输入的是汉字还是数字
   * 
   * @return 1为数字,2为字母,3为汉字
   * */
  public static int judgeText(String text) {
    Pattern p = Pattern.compile("[0-9]*");
    Matcher m = p.matcher(text);
    if (m.matches()) {
      return 1;
    }
    p = Pattern.compile("[a-zA-Z]");
    m = p.matcher(text);
    if (m.matches()) {
      return 2;
    }
    p = Pattern.compile("[\u4e00-\u9fa5]");
    m = p.matcher(text);
    if (m.matches()) {
      return 3;
    }
    return 0;
  }

  /**
   * @category 把字符串数组转为一个字符串,之间添加","逗号
   * 
   * @return 返回字符串
   * */
  public static String arrayAsString(String[] array) {
    /* 拼接字符 */
    List<String> b = new ArrayList<String>();
    for (String str : array) {
      b.add("" + str + ",");
    }
    /* 把字符串数组转为一个字符串 */
    StringBuffer sbf = new StringBuffer();
    for (int i = 0; i < b.size(); i++) {
      sbf.append(b.get(i));
    }
    /* 去掉最后一个逗号, */
    String newStr = sbf.toString();
    Log.i(TAG, "标签字符串:" + newStr);
    String productStr = newStr.substring(0, newStr.length() - 1);

    return productStr;
  }

  /**
   * @category 把list转为一个字符串,之间添加","逗号
   * 
   * @return 返回字符串
   * */
  public static String listAsString(List<?> array) {
    /* 拼接字符 */
    List<String> b = new ArrayList<String>();
    for (int i = 0; i < array.size(); i++) {
      b.add("" + array.get(i).toString() + ",");
    }
    /* 把字符串数组转为一个字符串 */
    StringBuffer sbf = new StringBuffer();
    for (int i = 0; i < b.size(); i++) {
      sbf.append(b.get(i));
    }
    /* 去掉最后一个逗号, */
    String newStr = sbf.toString();
    Log.i(TAG, "标签字符串:" + newStr);
    String productStr = newStr.substring(0, newStr.length() - 1);

    return productStr;
  }

  /**
   * @category 把字符串转为数组
   * 
   * @return 返回数组
   * */
  public static String[] StringAsArray(String text) {
    String[] stringArr = text.split(","); // 注意分隔符是需要转译滴...
    return stringArr;
  }

  /**
   * 将分为单位的转换为元并返回金额格式的字符串 （除100）
   * 
   * @param amount
   * @return
   * @throws Exception
   */
  /** 金额为分的格式 */
  public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

  public static String changeF2Y(Long amount) throws Exception {
    if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
      throw new Exception("金额格式有误");
    }

    int flag = 0;
    String amString = amount.toString();
    if (amString.charAt(0) == '-') {
      flag = 1;
      amString = amString.substring(1);
    }
    StringBuffer result = new StringBuffer();
    if (amString.length() == 1) {
      result.append("0.0").append(amString);
    } else if (amString.length() == 2) {
      result.append("0.").append(amString);
    } else {
      String intString = amString.substring(0, amString.length() - 2);
      for (int i = 1; i <= intString.length(); i++) {
        if ((i - 1) % 3 == 0 && i != 1) {
          result.append(",");
        }
        result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
      }
      result.reverse().append(".").append(amString.substring(amString.length() - 2));
    }
    if (flag == 1) {
      return "-" + result.toString();
    } else {
      return result.toString();
    }
  }

  /**
   * @category 获取设备型号
   * 
   * @return 返回设备型号
   * */
  public static String getModel() {
    Log.i(TAG, "设备型号:" + android.os.Build.MODEL);
    return android.os.Build.MODEL;

  }

  /**
   * @category 获取设备mac地址
   * 
   * */
  public static String getMacAddress(Context mContext) {
    WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    WifiInfo info = wifi.getConnectionInfo();
    String mac = info.getMacAddress().replace(":", "");
    String[] str = polictParameterTwo(mac);
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length; i++) {
      sb.append(str[i].toUpperCase());
    }
    Log.i(TAG, "Mac地址:" + sb.toString());
    return sb.toString();

  }

  public static String[] polictParameterTwo(String polictParameter) {
    String[] str = new String[polictParameter.length()];
    for (int i = 0; i < polictParameter.length(); i++) {
      // 取得字符串中的第i个字符赋值给字符串数组
      str[i] = polictParameter.substring(i, i + 1);
    }
    return str;
  }

  /**
   * @category 重新设置listview高度
   * 
   * */
  public static void getTotalHeightofListView(ListView listView) {
    ListAdapter mAdapter = listView.getAdapter();
    if (mAdapter == null) {
      return;
    }
    int totalHeight = 0;
    for (int i = 0; i < mAdapter.getCount(); i++) {
      View mView = mAdapter.getView(i, null, listView);
      mView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
          MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
      // mView.measure(0, 0);
      int height = mView.getMeasuredHeight();
      totalHeight += height;
      Log.w("HEIGHT" + i, String.valueOf(totalHeight));
    }
    ViewGroup.LayoutParams params = listView.getLayoutParams();
    params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
    listView.setLayoutParams(params);
    listView.requestLayout();
  }

  /**
   * @category 获取下载链接文件名 1、通过UrlConnection来获取 2、通过截取url地址最后一个"/"来获取
   * 
   * 
   * */
  public static String getFileName(String url) {
    String filename = "";
    // 从UrlConnection中获取文件名称
    // try {
    // URL myURL = new URL(url);
    //
    // URLConnection conn = myURL.openConnection();
    // if (conn == null) {
    // return null;
    // }
    // Map<String, List<String>> hf = conn.getHeaderFields();
    // if (hf == null) {
    // return null;
    // }
    // Set<String> key = hf.keySet();
    // if (key == null) {
    // return null;
    // }
    //
    // for (String skey : key) {
    // List<String> values = hf.get(skey);
    // for (String value : values) {
    // String result;
    // try {
    // result = new String(value.getBytes("ISO-8859-1"), "GBK");
    // int location = result.indexOf("filename");
    // if (location >= 0) {
    // result = result.substring(location + "filename".length());
    // filename = result.substring(result.indexOf("=") + 1);
    // isok = true;
    // }
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // }// ISO-8859-1 UTF-8 gb2312
    // }
    // if (isok) {
    // break;
    // }
    // }
    // } catch (MalformedURLException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // 2.从路径中获取
    if (filename == null || "".equals(filename)) {
      filename = url.substring(url.lastIndexOf("/") + 1);
    }
    return filename;
  }

  /**
   * @category 获取文件名与后缀名
   * 
   * @param 文件地址
   * 
   * */
  public static String getSuffix(String url) {
    String subString = "";
    String suffixes = "docx|pptx|xlsx|pdf|doc";
    Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");// 正则判断
    Matcher mc = pat.matcher(url);// 条件匹配
    while (mc.find()) {
      subString = mc.group();// 截取文件名后缀名
      Log.e("substring:", subString);
    }
    return subString;
  }

  /**
   * @category 获取本地保存的文件名
   * 
   * */
  public static String getLocalFileName(String fileName) {
    // 文件名
    String saveFileName = null;
    if (!isNull(fileName)) {
      saveFileName = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
    }

    return saveFileName;

  }

  /**
   * @category 下载文件
   * 
   * @param urlStr
   *          :下载链接,saveFileName:保存本地的文件名,mHnadler:处理下载状态
   * */
  private static boolean isCountinue = true;

  @SuppressWarnings("unused")
  public static String downloadFile(String urlStr, String fileName, Handler mHandler) {
    // 文件名
    String saveFileName = StaticField.SDCARD_FILE + fileName;
    Log.i(TAG, "fileName:" + saveFileName);
    try {
      // FileDES des = new FileDES(StaticField.DES_KEY);
      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.connect();
      int length = conn.getContentLength();
      InputStream is = conn.getInputStream();
      File file = new File(saveFileName);
      file.createNewFile();
      FileOutputStream fos = new FileOutputStream(file);

      int count = 0;
      byte buf[] = new byte[1024];

      do {
        int numRead = 0;
        if (buf != null) {
          numRead = is.read(buf);
        }
        /* 加密 */
        // des.doEncryptFile(is, saveFileName);
        count += numRead;
        // progress = (int) (((float) count / length) * 100);
        /* 更新进度 */
        mHandler.sendEmptyMessage(2);
        if (numRead <= 0) {
          /* 下载完成通知安装 */
          mHandler.sendEmptyMessage(1);
          break;
        }
        fos.write(buf, 0, numRead);
      } while (isCountinue);// 点击取消就停止下载.

      fos.close();
      is.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return saveFileName;
  }

  /**
   * @category Get PDF file Intent
   */
  public static Intent getPdfFileIntent(String path) {
    Intent i = new Intent(Intent.ACTION_VIEW);

    i.addCategory(Intent.CATEGORY_DEFAULT);
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Uri uri = Uri.fromFile(new File(path));
    i.setDataAndType(uri, "application/pdf");

    return i;
  }

  /**
   * @category Get PDF file Intent
   */
  public static Intent getVideoFileIntent(String path) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    String type = "video/*";
    Uri uri = Uri.parse(path);
    intent.setDataAndType(uri, type);
    return intent;
  }

  /**
   * @category 判断文件是否存在
   * 
   * @param filename
   *          :文件路径与文件名
   * @return false代表不存在,true代表已存在
   * */
  public static boolean fileIsExists(String fileName) {
    try {
      File f = new File(StaticField.SDCARD_FILE + fileName);
      if (!f.exists()) {
        return false;
      }
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
    return true;
  }

  /**
   * @category 截取特定某个字符最后位置后的字符串
   * 
   * */
  public static String getIndexOfStr(String str) {
    int i = str.lastIndexOf(".");
    String s = str.substring(i + 1, str.length());
    return s;
  }

  /**
   * @category 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
   * 
   * @param listView
   */
  public static void setListViewHeight(ListView listView) {
    // 获取ListView对应的Adapter
    ListAdapter listAdapter = listView.getAdapter();
    if (listAdapter == null) {
      return;
    }
    int totalHeight = 0;
    for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
      View listItem = listAdapter.getView(i, null, listView);
      listItem.measure(0, 0); // 计算子项View 的宽高
      totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
    }

    ViewGroup.LayoutParams params = listView.getLayoutParams();
    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    listView.setLayoutParams(params);
  }

  /**
   * @category 获取Sdcard的可用存储空间
   * 
   * @param sd卡地址
   * @return
   * */
  public static long getSdCardBlock(Context con, String path) {
    StatFs fs = new StatFs(path);
    long space = (fs.getAvailableBytes() / 1024) / 1024;
    return space;
  }

  /**
   * @category 弹出提示框
   * @param c
   *          :false不显示取消按钮,true为显示
   * */
  @SuppressWarnings("deprecation")
  public static void showHD(Activity mActivity, String text, boolean c) {
    final Dialog dialog = new Dialog(mActivity, R.style.Dialog);
    View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_hint, null);
    Button btn_cancle = (Button) view.findViewById(R.id.btn_cancle);
    Button btn_confrim = (Button) view.findViewById(R.id.btn_confrim);
    TextView tv_hint = (TextView) view.findViewById(R.id.tv_msg);
    tv_hint.setText(text);

    dialog.setCancelable(false);
    dialog.setCanceledOnTouchOutside(false);
    dialog.setContentView(view);
    dialog.show();

    WindowManager windowManager = mActivity.getWindowManager();
    Display display = windowManager.getDefaultDisplay();
    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
    lp.width = (int) (display.getWidth());
    dialog.getWindow().setAttributes(lp);

    Window dialogWindow = dialog.getWindow();
    if (c) {
      btn_cancle.setVisibility(View.GONE);
    } else {
      btn_cancle.setVisibility(View.VISIBLE);
    }
    dialogWindow.setGravity(Gravity.CENTER);
    btn_confrim.requestFocus();
    btn_cancle.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {
        dialog.dismiss();
      }
    });

    btn_confrim.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {
        dialog.dismiss();
      }
    });
  }

  /**
   * @category 删除文件夹下的文件
   * */
  public static void deleteDir(String path) {
    File dir = new File(path);
    if (dir == null || !dir.exists() || !dir.isDirectory())
      return;

    for (File file : dir.listFiles()) {
      if (file.isFile())
        file.delete(); // 删除所有文件
      else if (file.isDirectory())
        deleteDir(path); // 递规的方式删除文件夹
    }
    // dir.delete();// 删除目录本身
  }

  /**
   * @category 去掉字符串的前两个字符和最后一个字符
   * 
   * */
  public static String subStrFristAndLast(String text) {
    String txt = text.substring(2, text.length() - 1);
    return txt;
  }

  /**
   * @category 去掉字符串第一个字符
   * 
   * */
  public static String subStrFrist(String text) {
    String txt = text.substring(1, text.length());
    return txt;
  }

  /**
   * @category 获取指定日期是星期几 参数为null时表示获取当前日期是星期几
   * 
   * @param date
   * @return
   */
  public static String getWeekOfDate(String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    Date date = null;
    try {
      date = sdf.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    Calendar calendar = Calendar.getInstance();
    if (date != null) {
      calendar.setTime(date);
    }
    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    if (w < 0) {
      w = 0;
    }
    return weekOfDays[w];
  }

  public static String getDateFor(String str) {
    String currD = getTimeMillisByDate(str);
    String curr = getStrDate(currD);
    return curr;
  }

  /**
   * @category 获取当前的系统日期(年月日)
   * */
  public static String getCurrentDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date curDate = new Date(System.currentTimeMillis());
    String str = formatter.format(curDate);

    return str;
  }

  /**
   * @category 把字符串转为时间,获取月份
   * 
   * */
  @SuppressWarnings("deprecation")
  public static String getMonth(String str) {
    Date date = null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    int month = 0;
    try {
      date = format.parse(str);
      month = date.getMonth() + 1;
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return String.valueOf(month);
  }

  public static String dateFordate(String date) {
    String d = date;
    String t = d.replace("-", "/").toString();
    return t;
  }

  // public static void canGetmoreX(XListView listView, boolean bol) {
  // listView.setPullLoadEnable(bol);
  // listView.setPullRefreshEnable(bol);
  // }
  //
  // public static void onLoadX(XListView listView) {
  // listView.stopRefresh();
  // listView.stopLoadMore();
  // }

  /**
   * @category 获取本地图片转为bitmap
   * */
  public static Bitmap getDiskBitmap(String pathString) {
    Bitmap bitmap = null;
    try {
      File file = new File(pathString);
      if (file.exists()) {
        bitmap = BitmapFactory.decodeFile(pathString);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return bitmap;
  }

  /**
   * @category 获取当前系统时间戳
   */
  public static String getCurrTimestamp() {
    return System.currentTimeMillis() + "";
  }

  /**
   * @category 登录信息保存本地
   * */
  // public static void setSharedPreferencesInfo(Context mContext, UserBean
  // userInfo) {
  // SharedPreferences shared = mContext.getSharedPreferences("userInfo", 0);
  // SharedPreferences.Editor editor = shared.edit();
  // editor.putString("username", userInfo.getUsername());
  // editor.putString("name", userInfo.getName());
  // editor.putString("token", userInfo.getAccess_token());
  // editor.putString("user_id", userInfo.get_id());
  // editor.putString("date", Tools.getCurrentTime());
  // editor.putString("warehouse", userInfo.getWarehouse());
  // editor.putString("shop", userInfo.getShop());
  // editor.commit();
  // }

  /**
   * @category 此方法判断软键盘是否打开,如果是打开状态则关闭,关闭状态则打开
   * */
  public static void toggleSoftInput(Context mContext) {
    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
  }

  /**
   * @category 转换时间格式UTC 2015-12-7T16:00:00.000Z date
   * */
  public static String newForDate(String date) {
    date = date.replace("Z", " UTC");// 注意是空格+UTC
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
    Date d = null;
    try {
      d = format.parse(date);
      return d.toLocaleString();
    } catch (ParseException e) {
      Log.e(TAG, "", e);
    }
    return d.toLocaleString();
  }

  /**
   * XlistView停止刷新动作
   */
  public static void xlvStopLoad(XListView listView) {
    listView.stopRefresh();
    listView.stopLoadMore();
  }

  /**
   * 获取手机中所有的应用信息
   * */
  public static List<ApplicationInfo> getApplicationPackages(Context context){
    PackageManager packageManager = context.getPackageManager();
    return packageManager.getInstalledApplications(0);
  }
}
