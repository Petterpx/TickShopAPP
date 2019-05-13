package com.petterp.latte_ui.retyclear;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author Petterp on 2019/4/23
 * Summary:简单Holder
 * 邮箱：1509492795@qq.com
 */
public class MulitipleViewHolder extends BaseViewHolder {
    public MulitipleViewHolder(View view) {
        super(view);
    }

    public static MulitipleViewHolder create(View view) {
        return new MulitipleViewHolder(view);
    }
}
