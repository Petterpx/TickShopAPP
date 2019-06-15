package com.petterp.latte_core.util.Bootomzhaungtai;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.petterp.latte_core.app.Latte;

/**
 * @author Petterp on 2019/4/26
 * Summary:状态栏的工具类
 * 邮箱：1509492795@qq.com
 */
public final class EachTooblar extends RecyclerView.OnScrollListener {
    private Toolbar mToolbar;
    @SuppressLint("StaticFieldLeak")
    private static Activity ACTIVITY;
    private AppCompatEditText mEditText;
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);
    private final RgbValue RGB_SCROLL = RgbValue.create(255, 255, 255);
    private int dys;
    @SuppressLint("StaticFieldLeak")
    private static  EachTooblar eachTooblar=new EachTooblar();
    private static boolean mode;

    private EachTooblar() {

    }

    /**
     * RecyclearView滑动事件监听
     *
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        boolean b = recyclerView.canScrollVertically(-1);
        dys += dy;
        mode=b;
        if (!b) {
            mToolbar.setBackgroundColor(android.R.color.transparent);
            setAndroidNativeLightStatusBar(true);
            dys = 0;
        } else {
            if (dys > mToolbar.getHeight()) {
                mToolbar.setBackgroundColor(Color.parseColor("#f97a1f"));
            } else if (dys > 0 && dys <= mToolbar.getHeight()) {
                final float scale = (float) dys / mToolbar.getHeight();
                final float alpha = scale * 255;
                mToolbar.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
//                mEditText.setBackgroundColor(Color.argb((int) alpha, RGB_SCROLL.red(), RGB_SCROLL.green(), RGB_SCROLL.blue()));
            }
            if (dys >= mToolbar.getHeight()) {
                setAndroidNativeLightStatusBar(false);
            }
        }
    }

    /**
     * 设置状态栏扩容
     *
     * @param left
     * @param right
     */
    public EachTooblar setTooblarPadding(int left, int right) {
        mToolbar.setPadding(0, getStatusBarHeight(), 0, getStatusBarHeight() / 2);
        return this;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = Latte.getApplication().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = Latte.getApplication().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public EachTooblar setToolbar(Toolbar toolbar, AppCompatEditText editText) {
        this.mToolbar = toolbar;
        this.mEditText = editText;
        return this;
    }

    public EachTooblar setToolbar(Toolbar toolbar) {
        this.mToolbar = toolbar;
        return this;
    }


   /* public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
            transparencyBar(activity);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);
        }
    }*/

    /**
     * 修改状态栏为全透明
     *  
     *
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public EachTooblar setActivity(Activity activity) {
        ACTIVITY = activity;
        return this;
    }

    public static Activity getActivity() {
        return ACTIVITY;
    }

    /**
     * 设置状态栏背景颜色
     *
     * @param colorId
     */
    public void setmTooblarColor(int colorId) {
        Window window = ACTIVITY.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ACTIVITY.getResources().getColor(colorId));
        }
    }


    public EachTooblar setAndroidNativeLightStatusBar(boolean dark) {
        View decor = getActivity().getWindow().getDecorView();
        if (dark) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
        return this;
    }

    public EachTooblar setNavtooblar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ACTIVITY.getWindow().setNavigationBarColor(Color.parseColor("#fcfcfc"));
        }
        return this;
    }

    public static EachTooblar builder() {
            return eachTooblar;
    }

    public static final class Client {
        public static EachTooblar init( boolean mode) {
          return   EachTooblar.builder()
                    .setAndroidNativeLightStatusBar(mode)
                    .setTooblarPadding(0, 0);
        }
    }
    public static boolean getLocation(){
        return mode;
    }
}
