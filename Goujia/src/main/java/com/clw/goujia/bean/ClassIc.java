package com.clw.goujia.bean;

import java.io.Serializable;
import java.util.List;

public class ClassIc implements Serializable{
  private static final long serialVersionUID = 1L;
  private static final String TAG = "Product";
  
  private String _id;
  private String classic;
  private List<Product> products;
  
  
  
  public List<Product> getProducts() {
    return this.products;
  }
  public void setProducts(List<Product> products) {
    this.products = products;
  }
  public String get_id() {
    return this._id;
  }
  public void set_id(String _id) {
    this._id = _id;
  }
  public String getClassic() {
    return this.classic;
  }
  public void setClassic(String classic) {
    this.classic = classic;
  }
  
  
  
}
