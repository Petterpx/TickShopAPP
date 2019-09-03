package com.petterp.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.ItemType;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/27
 * Summary:数据转化
 * 邮箱：1509492795@qq.com
 */
public final class VerticalListDateConverter extends DataConverter {
    @Override
    public ArrayList<MulitpleItemEntity> convert() {
        final ArrayList<MulitpleItemEntity> dataList=new ArrayList<>();
        final JSONArray dataArray=JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final  int size=dataArray.size();
        for (int i=0;i<size;i++){
            final JSONObject data=dataArray.getJSONObject(i);
            final int id=data.getInteger("id");
            final String name=data.getString("name");

            final MulitpleItemEntity entity=MulitpleItemEntity.builder()
                    .setField(MultipleFidls.ITEM_TYPE,ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFidls.ID,id)
                    .setField(MultipleFidls.TEXT,name)
                    .setField(MultipleFidls.TAG,false)
                    .build();
            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setFild(MultipleFidls.TAG,true);
        }
        return dataList;
    }
}
