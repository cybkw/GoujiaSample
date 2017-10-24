package com.clw.goujia.adapter;

import java.util.ArrayList;
import java.util.List;

import com.clw.goujia.R;
import com.clw.goujia.bean.ClassIc;
import com.clw.goujia.bean.Product;
import com.clw.goujia.gjbk.GjbkDetails;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class BkDetailsAdapter extends BaseExpandableListAdapter {
  private static final String TAG = "BkDetailsAdapter";

  private Context mContext;

  private LayoutInflater mLayoutInflater;

  private List<ClassIc> groupList;

  private List<List<Product>> childList;
  private List<Product> products=new ArrayList<Product>();

  // private List<Product> childList;

  public BkDetailsAdapter(Context mContext, List<ClassIc> groupList, List<List<Product>> childList) {
    this.mContext = mContext;
    this.groupList = groupList;
    this.childList = childList;
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return childList.get(groupPosition).get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView,
      ViewGroup parent) {
    ChildView childView;
    if (null == convertView) {
      childView = new ChildView();
      convertView = mLayoutInflater.inflate(R.layout.item_child, null);
      childView.tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);
      childView.tv_color = (TextView) convertView.findViewById(R.id.tv_color);
      childView.tv_material = (TextView) convertView.findViewById(R.id.tv_material);
      childView.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
      childView.cb_check = (CheckBox) convertView.findViewById(R.id.cb_check);
      childView.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
      convertView.setTag(childView);
    } else {
      childView = (ChildView) convertView.getTag();
    }

    childView.cb_check.setChecked(childList.get(groupPosition).get(childPosition).isCheck());
    childView.tv_brand.setText("品牌:" + childList.get(groupPosition).get(childPosition).getBrand());
    childView.tv_color.setText("颜色:" + childList.get(groupPosition).get(childPosition).getColor());
    childView.tv_material.setText("材质:" + childList.get(groupPosition).get(childPosition).getMaterial());
    childView.tv_price.setText("价格:￥" + childList.get(groupPosition).get(childPosition).getPrice());
    childView.tv_size.setText("尺寸:" + childList.get(groupPosition).get(childPosition).getSize());

  
//    childView.cb_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//      @Override
//      public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
//        if (arg1) {
//          Log.i(TAG,  childList.get(groupPosition).get(childPosition).getBrand());
//          Product product=new Product();
//          product.set_id( childList.get(groupPosition).get(childPosition).get_id());
//          product.setBrand(childList.get(groupPosition).get(childPosition).getBrand());
//          product.setColor(childList.get(groupPosition).get(childPosition).getColor());
//          product.setMaterial(childList.get(groupPosition).get(childPosition).getMaterial());
//          product.setPrice(childList.get(groupPosition).get(childPosition).getPrice());
//          product.setSize(childList.get(groupPosition).get(childPosition).getSize());
//          products.add(product);
//          Log.i(TAG, products.size()+"add.");
//          GjbkDetails.getTotal(products);
//        } else {
//          Log.i(TAG,  childList.get(groupPosition).get(childPosition).getSize());
//          if (products.size()>0) {
//            for (int i = 0; i < products.size(); i++) {
//              if (products.get(i).get_id().equals(childList.get(groupPosition).get(childPosition).get_id())) {
//                products.remove(i);
//                i--;
//              }
//            }
//            GjbkDetails.getTotal(products);
//            Log.i(TAG, products.size()+"delete.");
//          }
//        }
//      }
//    });

    return convertView;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    if (childList.size() > 0) {
      return childList.size();
    } else {
      return 0;
    }

  }

  @Override
  public Object getGroup(int groupPosition) {
    return groupList.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groupList.size();
  }

  @Override
  public long getGroupId(int groupPsition) {
    return groupPsition;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    GroupView groupView;
    if (null == convertView) {
      groupView = new GroupView();
      convertView = mLayoutInflater.inflate(R.layout.item_group, null);
      groupView.tv_class = (TextView) convertView.findViewById(R.id.tv_class);
      convertView.setTag(groupView);
    } else {
      groupView = (GroupView) convertView.getTag();
    }

    groupView.tv_class.setText(groupList.get(groupPosition).getClassic());

    return convertView;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int arg0, int arg1) {
    return true;
  }

  private class GroupView {
    TextView tv_class;
  }

  private class ChildView {
    TextView tv_brand;
    TextView tv_size;
    TextView tv_color;
    TextView tv_material;
    CheckBox cb_check;
    TextView tv_price;
  }

}
