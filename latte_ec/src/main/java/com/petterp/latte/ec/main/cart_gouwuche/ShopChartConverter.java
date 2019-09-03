package com.petterp.latte.ec.main.cart_gouwuche;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class ShopChartConverter extends DataConverter {
    @Override
    public ArrayList<MulitpleItemEntity> convert() {
        final ArrayList<MulitpleItemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
            for (int i = 0; i < size; i++) {
                final JSONObject data = dataArray.getJSONObject(i);
                final String thumb = data.getString("thumb");
                final String desc = data.getString("desc");
                final String title = data.getString("title");
                final int id = data.getInteger("id");
                final int count = data.getInteger("count");
                final double price = data.getDouble("price");
                final MulitpleItemEntity entity = MulitpleItemEntity.builder()
                        .setField(MultipleFidls.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                        .setField(MultipleFidls.ID, id)
                        .setField(MultipleFidls.IMAGE_URL, thumb)
                        .setField(ShopCartItemFields.TITLE, title)
                        .setField(ShopCartItemFields.DESC, desc)
                        .setField(ShopCartItemFields.COUNT, count)
                        .setField(ShopCartItemFields.PRICE, price)
                        .setField(ShopCartItemFields.IS_SELECTED, false)     //默认未点击
                        .setField(ShopCartItemFields.POSITION, i)
                        .build();
                dataList.add(entity);
            }

        return dataList;
    }
}
