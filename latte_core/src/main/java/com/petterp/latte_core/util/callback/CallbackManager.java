package com.petterp.latte_core.util.callback;

import android.telecom.Call;

import java.util.WeakHashMap;

/**
 * @author Petterp on 2019/6/15
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class CallbackManager {
    private static final WeakHashMap<Object,IGlobalCallback> CALLBACKS=new WeakHashMap<>();

    private static class Holder{
        private static  final CallbackManager INSTANCE=new CallbackManager();
    }
    public static CallbackManager getInstance(){
        return Holder.INSTANCE;
    }
    public CallbackManager addCallback(Object tag,IGlobalCallback callback){
        CALLBACKS.put(tag,callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag){
        return CALLBACKS.get(tag);
    }

}
