package com.clw.goujia.bean;

public class LinkMan {
  private static final String TAG = "LinkMan";
  
  private String linkman;
  
  private String phone;
  
  private String storeName;
  
  private String address;
  
  private boolean isCheck;

  public boolean isCheck() {
    return this.isCheck;
  }

  public void setCheck(boolean isCheck) {
    this.isCheck = isCheck;
  }

  public String getLinkman() {
    return this.linkman;
  }

  public void setLinkman(String linkman) {
    this.linkman = linkman;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getStoreName() {
    return this.storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
  
  
}
