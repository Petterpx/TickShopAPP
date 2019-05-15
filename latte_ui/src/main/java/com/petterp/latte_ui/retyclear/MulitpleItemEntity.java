package com.petterp.latte_ui.retyclear;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * @author Petterp on 2019/4/23
 * Summary:Item的样式
 * 邮箱：1509492795@qq.com
 */
public class MulitpleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE=new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS=new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE=
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);


     MulitpleItemEntity(LinkedHashMap<Object,Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    /**
     * 控制Recyclear每一个item的样式
     * @return
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFidls.ITEM_TYPE);
    }

    //获取其他具体数据
    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }
    public final MultiItemEntity setFild(Object key,Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }
}
