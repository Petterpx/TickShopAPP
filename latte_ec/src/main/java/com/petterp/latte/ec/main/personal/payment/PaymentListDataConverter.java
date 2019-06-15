package com.petterp.latte.ec.main.personal.payment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte.ec.main.personal.order.OrderItemFields;
import com.petterp.latte.ec.main.personal.order.OrderListItemType;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/05/17
 * Summary:数据转化类
 * 邮箱：1509492795@qq.com
 */
public class PaymentListDataConverter extends DataConverter {
    @Override
    public ArrayList<MulitpleItemEntity> convert() {
        final JSONArray array=JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=array.size();
        for (int i=0;i<size;i++){
            //解析数据，存入Entry
            final JSONObject data=array.getJSONObject(i);
            final String thumb=data.getString("thumb");
            final String title=data.getString("title");
            final int id=data.getInteger("id");
            final double price=data.getDouble("price");
            final String time=data.getString("time");

            final MulitpleItemEntity entity=MulitpleItemEntity.builder()
                    .setItemType(PaymentListItemType.ITEM_PAYMENT_LIST)
                    .setField(PaymentFields.IS_SELECTED,false)
                    .setField(MultipleFidls.ID,id)
                    .setField(MultipleFidls.IMAGE_URL,thumb)
                    .setField(MultipleFidls.TITLE,title)
                    .setField(PaymentFields.PRICE,price)
                    .setField(PaymentFields.TIME,time)
                    .build();
            ENITIES.add(entity);
        }
        return ENITIES;
    }
}
