package com.petterp.latte_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte.R;
import com.petterp.latte.R2;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_core.util.Bootomzhaungtai.EachTooblar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Petterp on 2019/4/22
 * Summary:底部导航栏超类
 * 邮箱：1509492795@qq.com
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    //工具类对象
    private EachTooblar EACHTOOBLAR;
    //存储映射
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    //当前点击位置
    private int mCurrentDelegate = 0;
    //点击之后的颜色
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    /**
     * 存储map数据
     * 与EcBottomDelgate 关联，传入一个建造者初始化数据然后返回初始化数据
     *
     * @return
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    interface eachTooblar {
        Toolbar getTooblar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClickedColor = setClickedColor();
//        if (setClickedColor() != 0) {
//            mClickedColor = setClickedColor();
//        }

        final ItemBuilder builder = ItemBuilder.builder();
        //拿到map数据
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        //得到建造者传过来的数据
        ITEMS.putAll(items);
        //遍历map,对bootomd底部图片加文字分别进行映射(也就是将值传入两个集合，面向对象)
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    /**
     * 承接上面的初始化，开始绘制布局
     *
     * @param savedInstanceState
     * @param view
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        //省效率
        final int size = ITEMS.size();
        //取出每一个子布局，也就是每个小bar
        //默认展示是第一个界面(这个值只会使用一次)
        int mIndexDelegate = 0;
        for (int i = 0; i < size; i++) {
            //写入布局
            LayoutInflater.from(getContext()).inflate(R.layout.botton_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            //拿到
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTite = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);

            //初始化数据
            itemIcon.setText(bean.getICON());
            itemTite.setText(bean.getTITLE());

            //判断是否被选中
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTite.setTextColor(mClickedColor);
            }
        }
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        //开始加载第一个子布局，即首页,
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);

        //设置工具类的actvity
        EACHTOOBLAR = EachTooblar.builder().setActivity(getProxyActivity());
    }

    /**
     * 重置方法，每次点击之后,别的颜色
     */
    private void resetClear() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTite = (AppCompatTextView) item.getChildAt(1);
            itemTite.setTextColor(Color.GRAY);
            itemIcon.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetClear();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemTite = (AppCompatTextView) item.getChildAt(1);
        itemTite.setTextColor(mClickedColor);
        itemIcon.setTextColor(mClickedColor);
        //这一步隐藏布局并显示布局
        //第一个frgamen是需要显示的，第二个是需要隐藏的,注意先后顺序
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //更改当前点击位置
        mCurrentDelegate = tag;
        switch (tag) {
            case 0:
                //先判断上次RecyclearView的位置
                if (EachTooblar.getLocation()){
                    EACHTOOBLAR.setAndroidNativeLightStatusBar(false);
                }else{
                    EACHTOOBLAR.setAndroidNativeLightStatusBar(true);
                }
                break;
            case 1:
                EACHTOOBLAR.setAndroidNativeLightStatusBar(false);
                break;
            case 2:
                EACHTOOBLAR.setAndroidNativeLightStatusBar(false);
                break;
            case 3:
                EACHTOOBLAR.setAndroidNativeLightStatusBar(false);
                break;
            case 4:
                EACHTOOBLAR.setAndroidNativeLightStatusBar(false);
                break;
            default:
                break;
        }
    }
}
