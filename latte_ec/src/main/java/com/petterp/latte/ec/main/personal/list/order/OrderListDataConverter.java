package com.petterp.latte.ec.main.personal.list.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/30
 * Summary:个人中心数据转化类
 * 邮箱：1509492795@qq.com
 */
public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MulitpleItemEntity> convert() {
        final JSONArray array=JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=array.size();
        for (int i=0;i<size;i++){
            //解析数据，存入Entity
            final JSONObject data=array.getJSONObject(i);
            final String thumb=data.getString("thumb");
            final String title=data.getString("title");
            final int id=data.getInteger("id");
            final double price=data.getDouble("price");
            final String time=data.getString("time");

            final MulitpleItemEntity entity=MulitpleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFidls.ID,id)
                    .setField(MultipleFidls.IMAGE_URL,thumb)
                    .setField(MultipleFidls.TITLE,title)
                    .setField(OrderItemFields.PRICE,price)
                    .setField(OrderItemFields.TIME,time)
                    .build();
            ENITIES.add(entity);
        }
        return ENITIES;
    }
}
