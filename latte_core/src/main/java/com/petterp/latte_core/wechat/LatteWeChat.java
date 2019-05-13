package com.petterp.latte_core.wechat;

import android.app.Activity;

import com.petterp.latte_core.app.ConfigKeys;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.wechat.calltacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author Petterp on 2019/4/22
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class LatteWeChat {
    static final String APP_ID=Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET=Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback=null;

    //单例模式->懒汉
    private static final class Holder{
        private  static final LatteWeChat INSTANCE=new LatteWeChat();
    }


    /**
     * 返回LatteWechat对象，单例模式
     * @return
     */
    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }
    private LatteWeChat(){
        final Activity activity=Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI=WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public IWXAPI getWXAPI(){
        return WXAPI;
    }


    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback=callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }

    /**
     * 微信调用接口
     */
    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="";
        WXAPI.sendReq(req);
    }

}
