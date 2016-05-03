package com.example.rj.snappywheelview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Rj on 4/19/16.
 */
public class SnappyViewPager extends ViewPager {

  private float xdrop;
  private int pager_size;
  private int currentPage = 0;
  private int tempPosition = currentPage;
  private boolean stateChanged = false;


  public SnappyViewPager(Context context) {
    super(context);
    addOnPageChangeListener(pageChangeListener);
  }

  public SnappyViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    addOnPageChangeListener(pageChangeListener);
  }

  private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int offsetPixels) {
      if (position + 1 == currentPage) { // scrolling to next page
        if (currentPage > 0) {
          setUpTextAttrs(currentPage - 1, (1 - positionOffset) * 1 + 1, Utils.blendColors(
              ContextCompat.getColor(getContext(), R.color.black_54pc),
              ContextCompat.getColor(getContext(), R.color.home_alert_color),
              positionOffset));

          setUpTextAttrs(currentPage, (1 * positionOffset + 1), Utils.blendColors(
              ContextCompat.getColor(getContext(), R.color.home_alert_color),
              ContextCompat.getColor(getContext(), R.color.black_54pc),
              positionOffset));
          if (currentPage - 2 >= 0)
            setUpTextAttrs(currentPage - 2, 1, Utils.blendColors(
                ContextCompat.getColor(getContext(), R.color.black_24pc),
                ContextCompat.getColor(getContext(), R.color.black_54pc),
                positionOffset));
          if (currentPage + 1 < getAdapter().getCount()) {
            setUpTextAttrs(currentPage + 1, 1, Utils.blendColors(
                ContextCompat.getColor(getContext(), R.color.black_54pc),
                ContextCompat.getColor(getContext(), R.color.black_24pc),
                positionOffset));

          }


        }
      } else {
        if (currentPage == position) { // Scrolling to previous page
          if (position < getAdapter().getCount() - 1) {
            if (currentPage + 1 < getAdapter().getCount())
              setUpTextAttrs(currentPage + 1, 1 * positionOffset + 1, Utils.blendColors(
                  ContextCompat
                      .getColor(getContext(), R.color.home_alert_color),
                  ContextCompat.getColor(getContext(), R.color.black_54pc),
                  positionOffset));

            setUpTextAttrs(currentPage, (1 - positionOffset) * 1 + 1, Utils.blendColors(
                ContextCompat.getColor(getContext(), R.color.black_54pc),
                ContextCompat
                    .getColor(getContext(), R.color.home_alert_color),
                positionOffset));
            if (currentPage - 1 >= 0)
              setUpTextAttrs(currentPage - 1, 1, Utils.blendColors(
                  ContextCompat.getColor(getContext(), R.color.black_24pc),
                  ContextCompat.getColor(getContext(), R.color.black_54pc),
                  positionOffset));
            if (currentPage + 2 < getAdapter().getCount()) {
              setUpTextAttrs(currentPage + 2, 1, Utils.blendColors(
                  ContextCompat.getColor(getContext(), R.color.black_54pc),
                  ContextCompat.getColor(getContext(), R.color.black_24pc),
                  positionOffset));

            }

          }
        }
      }
    }

    @Override
    public void onPageSelected(int position) {
      tempPosition = position;
    }


    public void onPageScrollStateChanged(int state) {
      if (currentPage != tempPosition) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
          currentPage = tempPosition;
          stateChanged = false;
          settleSafely();
        } else if (ViewPager.SCROLL_STATE_SETTLING == state) {
          stateChanged = true;
        } else if (ViewPager.SCROLL_STATE_DRAGGING == state) {
          if (stateChanged) {
            stateChanged = false;
            currentPage = tempPosition;
            settleSafely();
          }
        }
      }
    }
  };


  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.w("count", " " + getAdapter().getCount());

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      xdrop = event.getX();
    }
    if (event.getAction() == MotionEvent.ACTION_MOVE) {
      getAdapter().getPageWidth(getCurrentItem());
      if (Math.abs(event.getX() - xdrop) < pager_size) {
        return super.onTouchEvent(event);
      } else {
        event.setAction(MotionEvent.ACTION_UP);
        return super.onTouchEvent(event);
      }
    }
    return super.onTouchEvent(event);
  }


  void settleSafely() {
    setUpTextAttrs(currentPage, 2, ContextCompat.getColor(getContext(), R.color.home_alert_color));
    final int BLACK_54_PC = ContextCompat.getColor(getContext(), R.color.black_54pc);
    final int BLACK_24_PC = ContextCompat.getColor(getContext(), R.color.black_24pc);
    if (currentPage + 1 < this.getAdapter().getCount()) {
      setUpTextAttrs(currentPage + 1, 1, BLACK_54_PC);
    }
    if (currentPage - 1 >= 0) {
      setUpTextAttrs(currentPage - 1, 1, BLACK_54_PC);
    }
    if (currentPage - 2 >= 0) {
      setUpTextAttrs(currentPage - 2, 1, BLACK_24_PC);
    }
    if (currentPage + 2 < this.getAdapter().getCount()) {
      setUpTextAttrs(currentPage + 2, 1, BLACK_24_PC);
    }
  }

  protected void setUpTextAttrs(int pageIndex, float scale, int color) {
    View parentView = this.getChildAt(pageIndex);
    if (parentView == null) {
      Log.w("hahaha", " " + pageIndex);
    }
    TextView textView = (TextView) parentView.findViewById(R.id.inner_view);
    textView.setTextColor(color);
    textView.setScaleX(scale);
    textView.setScaleY(scale);
  }

  public void setViewSize(double pager_Size) {
    pager_size = (int) pager_Size;
  }

}



