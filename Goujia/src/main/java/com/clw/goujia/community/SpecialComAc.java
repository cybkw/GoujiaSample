package com.clw.goujia.community;

import java.util.ArrayList;
import java.util.List;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.textservice.TextInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.inject.InjectView;
import com.clw.goujia.App;
import com.clw.goujia.BaseAc;
import com.clw.goujia.R;
import com.clw.goujia.adapter.CommentAdapter;
import com.clw.goujia.bean.Comment;
import com.clw.goujia.ui.XListView;
import com.clw.goujia.ui.XListView.IXListViewListener;
import com.clw.goujia.utils.Tools;

/**
 * 楼盘特色社区详情
 * */
@InjectLayer(R.layout.ac_specialcom)
public class SpecialComAc extends BaseAc implements OnClickListener, IXListViewListener, OnEditorActionListener {
  private static final String TAG = "SpecialComAc";

  @InjectView
  private ImageButton ib_back;

  @InjectView
  private TextView tv_title;

  @InjectView
  private XListView xlv_comment;

  private List<Comment> mComments = new ArrayList<Comment>();

  private CommentAdapter mCommentAdapter = null;

  private TextView tv_comment;

  private TextView tv_like;

  private ImageView iv_image_big;

  // 评论
  @InjectView
  private EditText et_comment;
  private String mComment = "";

  // 发送
  @InjectView
  private TextView tv_send;

  @InjectInit
  private void init() {
    mActivity = SpecialComAc.this;

    Tools.setFontStyle(mActivity, tv_title);
    ib_back.setOnClickListener(this);
    tv_send.setOnClickListener(this);

    xlv_comment.setPullLoadEnable(true);
    xlv_comment.setPullRefreshEnable(false);
    xlv_comment.setXListViewListener(this);
    mCommentAdapter = new CommentAdapter(mActivity, mComments);
    xlv_comment.setAdapter(mCommentAdapter);
    et_comment.setOnEditorActionListener(this);

    initListHeadView();
    callService();
  }

  /**
   * 初始化 listView 头部View
   * */
  private void initListHeadView() {

    LinearLayout head_layout = (LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.head_specialcom, null);
    iv_image_big = (ImageView) head_layout.findViewById(R.id.iv_image);
    tv_comment = (TextView) head_layout.findViewById(R.id.tv_comment);
    tv_like = (TextView) head_layout.findViewById(R.id.tv_like);
    xlv_comment.addHeaderView(head_layout);

  }

  /**
   * @category 请求数据
   * */
  private void callService() {
    Comment comment1 = new Comment("晓东", "", "户型可以", "2016-5-21 12:00:21");
    mComments.add(comment1);
    Comment comment2 = new Comment("小明", "", "小区公园绿化不错!", "2016-5-23 13:10:11");
    mComments.add(comment2);
    Comment comment3 = new Comment("东方巴黎", "", "推荐", "2016-5-26 21:20:21");
    mComments.add(comment3);
    Comment comment4 = new Comment("浮生青石板", "", "有健身器材,老年人可住", "2016-5-29 15:01:41");
    mComments.add(comment4);

    mCommentAdapter.notifyDataSetChanged();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ib_back:
        finishAcMove();
        break;
      case R.id.tv_send:
        mComment = et_comment.getText().toString();
        sendComment();
        break;
      default:
        break;
    }
  }

  @Override
  public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
    if (arg1 == EditorInfo.IME_ACTION_SEND) {
      mComment = et_comment.getText().toString();
      sendComment();
    }
    return false;
  }

  /**
   * 发表评论
   * */
  private void sendComment() {
    if (!Tools.isNull(mComment)) {
      Comment c = new Comment("小编", "", mComment, Tools.getCurrentTime());
      mComments.add(c);
      mCommentAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onRefresh() {
  }

  @Override
  public void onLoadMore() {
    Tools.xlvStopLoad(xlv_comment);
    xlv_comment.setFooterHint("没有更多了");
  }

}
