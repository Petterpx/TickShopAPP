package com.petterp.latte.ec.main.personal.list;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.petterp.latte.ec.R;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.util.DpsityUtil.DensityUtil;

import java.util.Objects;

/**
 * @author Petterp on 2019/6/16
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class ListIconDialog extends Dialog {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();
    public String res;

    public ListIconDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_list_icon);
        PhotoView photoView = findViewById(R.id.img_icon_avatar);
        setCanceledOnTouchOutside(true);
//        Glide.with(getContext())
//                .load("http://101.132.64.249/data/name.png")
//                .apply(OPTIONS)
//                .into(photoView);
    }

    @Override
    public void show() {
        super.show();
        //设置图片正方形大小
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getContext().getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width =DensityUtil.getScreenWidth(Latte.getActivity()); // 宽度设置为屏幕的0.8
        lp.height =DensityUtil.getScreenWidth(Latte.getActivity()); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);

    }
}
