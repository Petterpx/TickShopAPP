package com.petterp.latte_core.util.callback;

/**
 * @author Petterp on 2019/6/15
 * Summary:剪裁之后的回调
 * 邮箱：1509492795@qq.com
 */
public interface IGlobalCallback <T>{
    void executeCallback(T args);
}
