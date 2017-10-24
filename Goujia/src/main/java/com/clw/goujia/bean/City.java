package com.clw.goujia.bean;

public class City {
  private static final String TAG = "City";

  private String name;
  private String pinyin;

  public City(String name, String pinyin) {
    super();
    this.name = name;
    this.pinyin = pinyin;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPinyin() {
    return this.pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

}
