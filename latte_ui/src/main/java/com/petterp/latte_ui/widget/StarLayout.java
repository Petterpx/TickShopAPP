package com.petterp.latte_ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte_core.util.log.LatteLogger;
import com.petterp.latte_ui.R;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Petterp on 2019/6/17
 * Summary:自定义view_评分小星星
 * 邮箱：1509492795@qq.com
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    //空星
    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    //实星
    private static final CharSequence ICON_SELECTED = "{fa-star}";
    private static final int STAR_TOTAL_COUNT = 5;
    private static final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        STARS.clear();
        initStarIcon();
    }


    /**
     * 初始化图标
     */
    private void initStarIcon() {
        //默认5颗星
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            //动态添加
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            //给每颗星星的标记
            star.setTag(R.id.star_count, i);
            //默认未选中
            star.setTag(R.id.star_is_select, false);
            star.setOnClickListener(this);
            STARS.add(star);
            //添加进布局
            addView(star);
        }
    }

    public int getStarCount(){
        int count=0;
        for (int i=0;i<STAR_TOTAL_COUNT;i++){
            final IconTextView star=STARS.get(i);
            final  boolean isSelect= (boolean) star.getTag(R.id.star_is_select);
            if (isSelect){
                ++count;
            }
        }
        return count;
    }

    private void selectStar(int count){
        for (int i=0;i<=count;i++){
            final IconTextView star=STARS.get(i);
            star.setText(ICON_SELECTED);
            star.setTextColor(Color.RED);
            star.setTag(R.id.star_is_select,true);
        }
    }

    private void unSelectStar(int count){
        for (int i=0;i<STAR_TOTAL_COUNT;i++){
            if (i>count){
                final  IconTextView star=STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_select,false);
            }
        }
    }

//    private void setStar(int count){
//        for (int i=0;i<STAR_TOTAL_COUNT;i++){
//            if (i>count){
//                final  IconTextView star=STARS.get(i);
//                star.setText(ICON_UN_SELECT);
//                star.setTextColor(Color.GRAY);
//                star.setTag(R.id.star_is_select,false);
//            }else{
//                final  IconTextView star=STARS.get(i);
//                star.setText(ICON_SELECTED);
//                star.setTextColor(Color.RED);
//                star.setTag(R.id.star_is_select,true);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        LatteLogger.e("demo","点击");
        final IconTextView star = (IconTextView) v;
        //获取第几个星
        final int count = (int) star.getTag(R.id.star_count);
        //获取是否点击状态
        final  boolean isSelect= (boolean) star.getTag(R.id.star_is_select);
        if (!isSelect){
            selectStar(count);
        }else {
            unSelectStar(count);
        }
    }


}
