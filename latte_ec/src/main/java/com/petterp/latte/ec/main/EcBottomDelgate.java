package com.petterp.latte.ec.main;

import android.graphics.Color;

import com.petterp.latte.ec.main.cart_gouwuche.ShopCartDelegate;
import com.petterp.latte.ec.main.discoaer.DiscoverDelegate;
import com.petterp.latte.ec.main.index.IndexDelegate;
import com.petterp.latte.ec.main.personal.PersonalDelegate;
import com.petterp.latte.ec.main.sort.SortDelegate;
import com.petterp.latte_core.delegates.bottom.BaseBottomDelegate;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.delegates.bottom.BottomTabBean;
import com.petterp.latte_core.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * @author Petterp on 2019/4/22
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class EcBottomDelgate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final  LinkedHashMap<BottomTabBean,BottomItemDelegate> items=new LinkedHashMap<>();
        //将布局添加到Map中
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
