package com.petterp.latte.ec.detall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author Petterp on 2019/4/27
 * Summary:商品详情
 *
 * 邮箱：1509492795@qq.com
 */
public class GoodsDetaillDeleagte extends LatteDelegate {
    public static GoodsDetaillDeleagte create(){
        return new GoodsDetaillDeleagte();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detall;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }


    /**
     * 动画
     * @return
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
