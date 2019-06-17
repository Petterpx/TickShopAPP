package com.petterp.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte_core.delegates.LatteDelegate;

/**
 * @author Petterp on 2019/6/17
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class OrderCommentDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
