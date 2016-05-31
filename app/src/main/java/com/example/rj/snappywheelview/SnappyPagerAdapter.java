package com.example.rj.snappywheelview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SnappyPagerAdapter extends PagerAdapter {

  private final Context context;
  private List<String> data = new ArrayList<>();
  private View currentCard;
  private int pager_size;
  private LayoutInflater inflater;

  public SnappyPagerAdapter(Context context) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    data.clear();
    data.add("0");
    data.add("1");
    data.add("2");
    data.add("3");
    data.add("4");
    data.add("5");
  }


  public void refresh() {
    notifyDataSetChanged();
  }

  @Override
  public int getItemPosition(Object object) {
    return POSITION_NONE;
  }

  @Override
  public int getCount() {
    return data.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void setPrimaryItem(ViewGroup container, int position, Object object) {
    super.setPrimaryItem(container, position, object);
    this.currentCard = (View) object;

  }

  @SuppressLint("InflateParams")
  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View cardView = inflater.inflate(R.layout.card, null, false);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        pager_size, ViewGroup.LayoutParams.WRAP_CONTENT);
    cardView.setLayoutParams(layoutParams);
    TextView viewData = (TextView) cardView.findViewById(R.id.inner_view);
    viewData.setText(data.get(position));
    container.addView(cardView);
    return cardView;
  }


  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  public View getCurrentCard() {
    return currentCard;
  }


  public void refreshData() {
    notifyDataSetChanged();
  }

  public void setViewSize(double pager_Size) {
    pager_size = (int) pager_Size;
  }


}