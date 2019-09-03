package com.petterp.latte_core.util.Bootomzhaungtai;

import com.google.auto.value.AutoValue;

/**
 * @author Petterp on 2019/4/24
 * Summary:存储红绿蓝色值
 * 邮箱：1509492795@qq.com
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();
    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red, green, blue);
    }
}
