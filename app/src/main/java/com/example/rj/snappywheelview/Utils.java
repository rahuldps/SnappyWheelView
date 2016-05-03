package com.example.rj.snappywheelview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Rj on 5/2/16.
 */
public class Utils {

  public static int getScreenWidthUsingDisplayMetrics(Context context) {
    DisplayMetrics displaymetrics = new DisplayMetrics();
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    wm.getDefaultDisplay().getMetrics(displaymetrics);
    return displaymetrics.widthPixels;
  }

  public static void setCurrentItemInViewPager(final ViewPager pager, final int position,
                                               final boolean shouldAnimate) {
    if (position >= 0 && null != pager) {
      pager.post(new Runnable() {

        @Override
        public void run() {
          pager.setCurrentItem(position, shouldAnimate);
        }
      });
    }
  }

  public static int blendColors(int startColor, int endColor, float ratio) {
    final float inverseRation = 1f - ratio;
    float a = (Color.alpha(startColor) * ratio) + (Color.alpha(endColor) * inverseRation);
    float r = (Color.red(startColor) * ratio) + (Color.red(endColor) * inverseRation);
    float g = (Color.green(startColor) * ratio) + (Color.green(endColor) * inverseRation);
    float b = (Color.blue(startColor) * ratio) + (Color.blue(endColor) * inverseRation);
    return Color.argb((int) a, (int) r, (int) g, (int) b);
  }
}