package com.petterp.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte_core.util.Bootomzhaungtai.RgbValue;

/**
 * @author Petterp on 2019/4/24
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar>{
    //顶部距离
    private int mDistanceY=0;
    //定义变化速度
    private static final int SHOW_SPEED=3;
    //定义变化的颜色
    private final RgbValue RGB_VALUE =RgbValue.create(255,124,2);

    //一定要有两个构造参数的方法，否则会报错
    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency.getId()==R.id.rv_index;
    }

    //消费事件
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }


    //处理具体的逻辑
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //增加滑动距离
        mDistanceY+=dy;
        Log.e("demo",mDistanceY+"");
        //获得toobar高度
        final int targetHeight=child.getBottom();
        Log.e("demo","toobar"+targetHeight+"");

        //当滑动时，并且距离小于 toobar 高度的时候，调整渐变色
        if (mDistanceY>0&&mDistanceY<=targetHeight){
            final float scale=(float) mDistanceY/targetHeight;
            final  float alpha=scale*255;
            child.setBackgroundColor(Color.argb((int) alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
            mDistanceY=0;
        }else if (mDistanceY>targetHeight){
             child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }
    }
}
