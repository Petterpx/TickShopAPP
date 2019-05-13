package com.petterp.latte.ec.pay;

/**
 * @author Petterp on 2019/4/29
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public interface IAlPayResultListener {
    //成功
    void onPaySuccess();
    //支付中
    void onPaying();
    //失败
    void onPayFail();
    //取消支付
    void onPayCancel();
    //网络错误
    void onPayConnectError();
}
