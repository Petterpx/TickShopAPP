package com.petterp.latte.ec.pay.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.petterp.latte.ec.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Petterp on 2019/4/30
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class TestPay implements View.OnClickListener{
    private AlertDialog mDialog = null;
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016093000634409";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCMdr9OEZa3t/4M6fbbr34o1u0uQz4wjewDPVIL2p+66a280WsO4zgdWz+xiulO2KP6LjSCdZUFEMgEZIKgaL1OxGjeZd9r6eQln1xxGNhDB1TL35W5WQv3kDJGzqEPvK5EfQWzfa216mNR3cpCZkP1S6K7wQ0Gq6yI0cJz5PRT+zxNMg1ggi1FzuRkSFoAbrrGW8LnnEwJRGTxgMB1ED5cNZX2oHyGAonWFb8DT+EGXQMfG1jDLBkZO8N0ad7YMd3qULYbuPekIXSY+VJP9P6LgA90kFU5HFGfTDoIBr8HYTbRw6bJETtF2FYi5k6zJxkMrjST4ITHhorw22FQXBPAgMBAAECggEAEkM+DbEcJVkIH7/XNu+k1EDN917R0/8iYp6etX55OM6DdzxOtKcxzraKEH7p1marH7asXkIk/2xMMi5kxvaCTlX31m+S8X13Ss/hTtkWe6GAZ4/M8BGDaTeK7OrV3YEa+RzsmVpQ1jsQPJIew/Hlt5PW0IRQmaJ4iOJr75Nfj8er0Ogv1nWtAW4QzMBHzIYialtawpoinJM98OyFeoCAJr2T2o7fC9jayj2PEgQQtM1TioCKBJoEvmHEx490mdkyFmpHT0DrrgIeOwFBAqVudT6LQtlc2Ze6iMNq0PZZcPKoMVg15vbPyKssHPa+25vQ7td9G3gz8TZSyTEfs3w/KQKBgQDMUpe6nbRD9w+xt1Aeoi2ple+rZNllo9irLsrnGFKv0vkN40Y5CJn9P/Egch/rRnSSVH6kdpv+Rrhl99r4haBa9ZJMBM028Fvks2HI7GoSM35Rs0LHAQfzcXZDfOVwYPcuoSEZum7k5NwB2Uu2YdG4U7RNDNz0QV5IJraJpaRkkwKBgQCjH6hdv04FJm1JOWDE7e4pi85PgG1K2lvB3j4ukbRJWnA/akiNHi6j3IFTO7pndYhbRt+cnVpaRkLSnwyYQYeeAOyBNnujUz26kfLEOpIOq+Wp4TtNzRB6lpElKJYFqIqjh/hdGvHzMQ6oCRHU6gD5bs0B5ah7kVvniATC/yp21QKBgGpACObiW3t8Xozr5p4Hd/dYIOnf560TJvjdvGWwIE+ORjUSmtHNx0YKmjllH3QZj0UI61Ja24O/AuUDnrrdwiWKzij51j1bgG3NWmxBhGcyn92cen1B7ACMYBN4P1fRFLkK0UuvxzsAR+guN1EATS6tWHU42y2pUH30o7ewi2xBAoGAa0KEveft0FsdHznYVIUyxiTEqugCdWaxUP/mmU57YoRSLJChVhFTj7GNZAJxJlhVKdenb17UK2npxH6KlI76Mr3exXPixlkUzNns7HSq13TWVebgzN6bCFB67hkK1HundbIRcH8oOsW2Im2cSQj5TQo7SRUOvPmumcaJgwKvlGUCgYEAsESnk8aVZvjBZYo8/hsN8zlGIcuWt37jRMfIP51TXqPjQdOWrQEkaE8mQvSyFtUmF687Fn53UXPePKYREeI9Htmg5Mpr0WY3pTet2lI6M218SAiPiZGgrhxbQn7NzsTCaljjXug1ZV1KkoD8TzO8D88A5fvMHXdh8exr6RelJsY=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    //支付宝需要Activity
    private final Activity ACTIVITY;
    //支付成功回调
    private IcartAlPay mIcartAlPay;

    //具体信息回调
    private String body;
    private String money;
    private String subject;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //回调接口
                        mIcartAlPay.SUCCESS();
                        Toast.makeText(ACTIVITY, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ACTIVITY, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(ACTIVITY,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(ACTIVITY,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    public TestPay(Activity activity,IcartAlPay icartAlPay) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        this.ACTIVITY = activity;
        this.mIcartAlPay=icartAlPay;
        this.mDialog = new AlertDialog.Builder(activity).create();
    }

    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(ACTIVITY).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", (dialoginterface, i) -> {
                        //
                        ACTIVITY.finish();
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2,money,body,subject);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(ACTIVITY);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Log.i("msp", result.toString());

            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //支付方法
    public void beginDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_oay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay) {
            payV2();
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            mDialog.cancel();

        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();

        }
    }

    public TestPay setPayInfo(HashMap<String,String> hashMap){
        this.body=hashMap.get("body");
        this.subject=hashMap.get("subject");
        this.money=hashMap.get("money");
        return this;
    }
}
