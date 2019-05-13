package com.petterp.latte_ui.retyclear;

import java.util.ArrayList;

/**
 * @author Petterp on 2019/4/23
 * Summary:数据的转化处理
 * MulitpleItemEntity的基类
 * 邮箱：1509492795@qq.com
 */
public abstract class DataConverter {

    protected  final ArrayList<MulitpleItemEntity> ENITIES=new ArrayList<>();
    private String mJsonData=null;

    public abstract ArrayList<MulitpleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData=json;
        return this;
    }

    public String getJsonData(){
        if (mJsonData == null||mJsonData.isEmpty()) {
            throw  new NullPointerException("Data is null");
        }
        return mJsonData;
    }

}
