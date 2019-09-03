package com.petterp.latte.ec.detail;

import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * @author Petterp on 2019/6/20
 * Summary:加入购物车后的小红圈-》放大缩小
 * 邮箱：1509492795@qq.com
 */
public class ScaleUpAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target,"scaleX",0.8f,1f,1.4f,1.2f,1),
                ObjectAnimator.ofFloat(target,"scaleY",0.8f,1f,1.4f,1.2f,1)
        );
    }
}
