package com.clw.goujia.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.gesture.*;

import com.clw.goujia.App;
import com.clw.goujia.R;
import com.polites.android.GestureImageView;

/**
 * 单张图片显示Fragment
 */
public class ImageDetailFragment extends Fragment {
  private String mImageUrl;
  private GestureImageView mImageView;

  public static ImageDetailFragment newInstance(String imageUrl) {
    final ImageDetailFragment f = new ImageDetailFragment();

    final Bundle args = new Bundle();
    args.putString("url", imageUrl);
    f.setArguments(args);

    return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View v = inflater.inflate(R.layout.image_fragment, container, false);
    mImageView=(GestureImageView) v.findViewById(R.id.image);
    App.LoadImage(mImageUrl, mImageView);
    return v;
  }

}
