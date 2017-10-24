package com.clw.goujia.bean;

import java.io.Serializable;

public class Product implements Serializable{
  private static final long serialVersionUID = 1L;
  private static final String TAG = "Product";
  
  private String _id;
  private String color;
  private String size;
  private String material;
  private String brand;
  private boolean isCheck;
  private double price;
  public String get_id() {
    return this._id;
  }
  public void set_id(String _id) {
    this._id = _id;
  }
  public String getColor() {
    return this.color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public String getSize() {
    return this.size;
  }
  public void setSize(String size) {
    this.size = size;
  }
  public String getMaterial() {
    return this.material;
  }
  public void setMaterial(String material) {
    this.material = material;
  }
  public String getBrand() {
    return this.brand;
  }
  public void setBrand(String brand) {
    this.brand = brand;
  }
  public boolean isCheck() {
    return this.isCheck;
  }
  public void setCheck(boolean isCheck) {
    this.isCheck = isCheck;
  }
  public double getPrice() {
    return this.price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  
  
}
