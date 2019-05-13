package com.petterp.latte.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.petterp.latte_ui.retyclear.DataConverter;
import com.petterp.latte_ui.retyclear.ItemType;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;


import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/23
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MulitpleItemEntity> convert() {
        final JSONArray dataArray =JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=dataArray.size();
        for (int i=0;i<size;i++){
            final JSONObject data=dataArray.getJSONObject(i);

            final String imageUrl=data.getString("imageUrl");
            final String text=data.getString("text");
            final  int spanSize=data.getInteger("spanSize");
            final  int id=data.getInteger("goodsId");
            final  JSONArray banners=data.getJSONArray("banners");

            final ArrayList<String> bannerImgae=new ArrayList<>();
            int type=0;
            if (imageUrl==null&&text!=null){
                type=ItemType.TEXT;
            }else if (imageUrl!=null&&text==null){
                type=ItemType.IMAGE;
            }else if (imageUrl!=null){
                type=ItemType.TEXT_IMAGE;
            }else if (banners!=null){
                type=ItemType.BANNER;
                //Banner初始化
                final int bannersize=banners.size();
                for (int j=0;j<bannersize;j++){
                    final String banner=banners.getString(i);
                    bannerImgae.add(banner);
                }
            }

            final MulitpleItemEntity entity=MulitpleItemEntity.builder()
                    .setField(MultipleFidls.ITEM_TYPE,type)
                    .setField(MultipleFidls.SPAN_SIZE,spanSize)
                    .setField(MultipleFidls.ID,id)
                    .setField(MultipleFidls.TEXT,text)
                    .setField(MultipleFidls.IMAGE_URL,imageUrl)
                    .setField(MultipleFidls.BANNERS,bannerImgae)
                    .build();


            ENITIES.add(entity);

        }
        return ENITIES;
    }
}
