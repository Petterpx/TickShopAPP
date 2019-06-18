package com.petterp.latte_core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.petterp.latte.R;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * @author Petterp on 2019/6/18
 * Summary:扫描框
 * 邮箱：1509492795@qq.com
 */
public class LatteViewFindView extends ViewFinderView {
    public LatteViewFindView(Context context) {
        this(context, null);
    }

    public LatteViewFindView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //扫描框为正方形
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }

}
