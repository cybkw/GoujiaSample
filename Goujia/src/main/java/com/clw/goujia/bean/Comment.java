package com.clw.goujia.bean;

public class Comment {
  private static final String TAG = "Comment";

  private String _id;
  private String name;
  private String headImg;
  private String content;
  private String date;
  
  public Comment(String name,String headImg,String content,String date){
    this.name=name;
    this.headImg=headImg;
    this.content=content;
    this.date=date;
  }

  public String get_id() {
    return this._id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHeadImg() {
    return this.headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}
