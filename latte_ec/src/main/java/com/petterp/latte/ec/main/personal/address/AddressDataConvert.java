package com.petterp.latte.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/6/16
 * Summary:收货地址数据转化
 * 邮箱：1509492795@qq.com
 */
public class AddressDataConvert extends DataConverter {
    @Override
    public ArrayList<MulitpleItemEntity> convert() {

        final JSONArray array=JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=array.size();
        for (int i=0;i<size;i++){
            final JSONObject data=array.getJSONObject(i);
            final int id=data.getInteger("id");
            final String name=data.getString("name");
            final String phone=data.getString("phone");
            final String address=data.getString("address");
            final boolean isDefault=data.getBoolean("default");

            final MulitpleItemEntity entity=MulitpleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFidls.ID,id)
                    .setField(MultipleFidls.NAME,name)
                    .setField(MultipleFidls.TAG,isDefault)
                    .setField(AddressItemFields.ADDRESS,address)
                    .setField(AddressItemFields.PHONE,phone)
                    .build();
            ENITIES.add(entity);
        }
        return ENITIES;
    }
}
