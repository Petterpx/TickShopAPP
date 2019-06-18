package com.petterp.latte.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/6/18
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SerachDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY="search_history";

    @Override
    public ArrayList<MulitpleItemEntity> convert() {
        final String jsonStr=LatterPreference.getCustomAppProfile("search_history");
        if (!jsonStr.equals("")){
            final JSONArray array=JSONArray.parseArray(jsonStr);
            final int size=array.size();
            for (int i=0;i<size;i++){
                final String hostoryItemText=array.getString(i);
                final MulitpleItemEntity build = MulitpleItemEntity.builder()
                        .setItemType(SerachType.ITEM_SEARCH)
                        .setField(MultipleFidls.TEXT, hostoryItemText)
                        .build();
                ENITIES.add(build);
            }
        }
        return ENITIES;
    }

}
