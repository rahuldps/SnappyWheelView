package com.example.rj.snappywheelview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  public static final double MARGIN_VIEW_PAGER = 38;
  public static final double VIEW_SIZE = 24;
  public static double PAGER_MARGIN;
  public static double PAGER_VIEW_SIZE;
  private int currentPage = 0;
  public SnappyViewPager pager;
  private SnappyPagerAdapter adapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    calculatePagerSizes();
    pager = (SnappyViewPager) findViewById(R.id.pager);
    pager.setPageMargin((int) (-1 * PAGER_MARGIN));
    pager.setViewSize(PAGER_VIEW_SIZE);
    pager.setOffscreenPageLimit(3);
    Utils.setCurrentItemInViewPager(pager, currentPage, false);
    adapter = new SnappyPagerAdapter(this);
    adapter.setViewSize(PAGER_VIEW_SIZE);
    pager.setAdapter(adapter);
  }

  public void calculatePagerSizes() {
    double screenWidth = Utils.getScreenWidthUsingDisplayMetrics(this);
    PAGER_VIEW_SIZE = ((screenWidth * VIEW_SIZE) / 100);
    PAGER_MARGIN = ((screenWidth * 2 * MARGIN_VIEW_PAGER) / 100);
  }

}


