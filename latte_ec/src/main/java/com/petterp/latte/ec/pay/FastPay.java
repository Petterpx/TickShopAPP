package com.petterp.latte.ec.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.petterp.latte.ec.R;
import com.petterp.latte_core.delegates.LatteDelegate;

/**
 * @author Petterp on 2019/4/29
 * Summary:Pay工具类
 * 邮箱：1509492795@qq.com
 */
public class FastPay implements View.OnClickListener {
    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener;
    private Activity mactivity = null;

    private AlertDialog mDialog = null;
    private int mOrderID = -1;

//    private AilPay ailPay;


    private FastPay(LatteDelegate delegate) {
        this.mactivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
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

    public FastPay setPayResultListener(IAlPayResultListener listener){
        this.mIAlPayResultListener=listener;
        return this;
    }
    public FastPay setRoderId(int  mOrderID){
        this.mOrderID=mOrderID;
        return this;
    }

    public final void alPay(int orderId){
        //签名串,自己写的签名串，返回相应的数据
        final String singUrl="";
        //获取签名字符串，用于解钥
//        RestClient.builder()
//                .url(singUrl)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final String paySign="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCMdr9OEZa3t/4M6fbbr34o1u0uQz4wjewDPVIL2p+66a280WsO4zgdWz+xiulO2KP6LjSCdZUFEMgEZIKgaL1OxGjeZd9r6eQln1xxGNhDB1TL35W5WQv3kDJGzqEPvK5EfQWzfa216mNR3cpCZkP1S6K7wQ0Gq6yI0cJz5PRT+zxNMg1ggi1FzuRkSFoAbrrGW8LnnEwJRGTxgMB1ED5cNZX2oHyGAonWFb8DT+EGXQMfG1jDLBkZO8N0ad7YMd3qULYbuPekIXSY+VJP9P6LgA90kFU5HFGfTDoIBr8HYTbRw6bJETtF2FYi5k6zJxkMrjST4ITHhorw22FQXBPAgMBAAECggEAEkM+DbEcJVkIH7/XNu+k1EDN917R0/8iYp6etX55OM6DdzxOtKcxzraKEH7p1marH7asXkIk/2xMMi5kxvaCTlX31m+S8X13Ss/hTtkWe6GAZ4/M8BGDaTeK7OrV3YEa+RzsmVpQ1jsQPJIew/Hlt5PW0IRQmaJ4iOJr75Nfj8er0Ogv1nWtAW4QzMBHzIYialtawpoinJM98OyFeoCAJr2T2o7fC9jayj2PEgQQtM1TioCKBJoEvmHEx490mdkyFmpHT0DrrgIeOwFBAqVudT6LQtlc2Ze6iMNq0PZZcPKoMVg15vbPyKssHPa+25vQ7td9G3gz8TZSyTEfs3w/KQKBgQDMUpe6nbRD9w+xt1Aeoi2ple+rZNllo9irLsrnGFKv0vkN40Y5CJn9P/Egch/rRnSSVH6kdpv+Rrhl99r4haBa9ZJMBM028Fvks2HI7GoSM35Rs0LHAQfzcXZDfOVwYPcuoSEZum7k5NwB2Uu2YdG4U7RNDNz0QV5IJraJpaRkkwKBgQCjH6hdv04FJm1JOWDE7e4pi85PgG1K2lvB3j4ukbRJWnA/akiNHi6j3IFTO7pndYhbRt+cnVpaRkLSnwyYQYeeAOyBNnujUz26kfLEOpIOq+Wp4TtNzRB6lpElKJYFqIqjh/hdGvHzMQ6oCRHU6gD5bs0B5ah7kVvniATC/yp21QKBgGpACObiW3t8Xozr5p4Hd/dYIOnf560TJvjdvGWwIE+ORjUSmtHNx0YKmjllH3QZj0UI61Ja24O/AuUDnrrdwiWKzij51j1bgG3NWmxBhGcyn92cen1B7ACMYBN4P1fRFLkK0UuvxzsAR+guN1EATS6tWHU42y2pUH30o7ewi2xBAoGAa0KEveft0FsdHznYVIUyxiTEqugCdWaxUP/mmU57YoRSLJChVhFTj7GNZAJxJlhVKdenb17UK2npxH6KlI76Mr3exXPixlkUzNns7HSq13TWVebgzN6bCFB67hkK1HundbIRcH8oOsW2Im2cSQj5TQo7SRUOvPmumcaJgwKvlGUCgYEAsESnk8aVZvjBZYo8/hsN8zlGIcuWt37jRMfIP51TXqPjQdOWrQEkaE8mQvSyFtUmF687Fn53UXPePKYREeI9Htmg5Mpr0WY3pTet2lI6M218SAiPiZGgrhxbQn7NzsTCaljjXug1ZV1KkoD8TzO8D88A5fvMHXdh8exr6RelJsY=";
//                        //必须是异步的调用客户端支付接口
//                        final PayAsmcTask payAsmcTask=new PayAsmcTask(mactivity,mIAlPayResultListener);
//                        //多线程池同时进行
//                        payAsmcTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,paySign);
//                    }
//                })
//                .build()
//                .post();
    }
   /* public FastPay fastPay(AilPay ailPay){
        this.ailPay=ailPay;
        return this;
    }*/
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay) {
//            ailPay.pay();
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            mDialog.cancel();

        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();

        }
    }

}
