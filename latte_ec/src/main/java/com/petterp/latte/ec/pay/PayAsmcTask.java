package com.petterp.latte.ec.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.ui.loader.LatteLoader;
import com.petterp.latte_core.util.log.LatteLogger;

/**
 * @author Petterp on 2019/4/29
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class PayAsmcTask extends AsyncTask<String, Void, String> {

    @SuppressLint("StaticFieldLeak")
    //支付宝的支付必须要有一个Activity
    private final Activity ACTIVITY;
    //回调接口
    private final IAlPayResultListener LISTENER;

    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单支付处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //取消支付
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //网络错误
    private static final String AL_PAY_STATUS_ERROR = "6002";

    //初始化
    public PayAsmcTask(Activity ACTIVITY, IAlPayResultListener LISTENER) {
        this.ACTIVITY = ACTIVITY;
        this.LISTENER = LISTENER;
    }


    //返回签名信息
    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        //允许验证
        return payTask.pay(alPaySign, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        LatteLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        //支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥子验证
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        LatteLogger.d("AL_PAY_RESULT", resultInfo);
        LatteLogger.d("AL_PAY_RESULT", resultStatus);
        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (LISTENER != null) {
                    LISTENER.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (LISTENER != null) {
                    LISTENER.onPaying();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (LISTENER != null) {
                    LISTENER.onPayFail();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (LISTENER != null) {
                    LISTENER.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_ERROR:
                if (LISTENER != null) {
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;
        }
    }
}
