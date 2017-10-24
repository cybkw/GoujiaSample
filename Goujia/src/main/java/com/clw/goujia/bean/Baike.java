package com.clw.goujia.bean;

public class Baike {
  private static final String TAG = "Baike";
  
  private String _id;
  
  private String title;
  private int img_url;
  private String info;
  public String get_id() {
    return this._id;
  }
  public void set_id(String _id) {
    this._id = _id;
  }
  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getInfo() {
    return this.info;
  }
  public void setInfo(String info) {
    this.info = info;
  }
  public int getImg_url() {
    return this.img_url;
  }
  public void setImg_url(int img_url) {
    this.img_url = img_url;
  }
  
  
}
