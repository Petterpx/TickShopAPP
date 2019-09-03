package com.petterp.latte_ui.retyclear;


import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * @author Petterp on 2019/4/24
 * Summary:RecyclearView分割线
 * 邮箱：1509492795@qq.com
 */
public class BaseDecoration extends DividerItemDecoration {
    //传入线的颜色，线的粗细
    public BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DivederLookupImpl(color,size));
    }
    public static BaseDecoration create(@ColorInt int color,int size){
        return new BaseDecoration(color, size);
    }
}
