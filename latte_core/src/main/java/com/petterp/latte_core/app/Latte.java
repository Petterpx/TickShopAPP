package com.petterp.latte_core.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:对外工具类
 */
public final class Latte {
    public static Configurator init(Context context){
        getConfiguration().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<Object,Object> getConfiguration(){
        return Configurator.getInstance().getLatteConfigs();
    }
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static Context getApplication(){
        return (Context) getConfiguration().get(ConfigKeys.APPLICATION_CONTEXT);
    }
    public static Activity getActivity(){
        return (Activity) getConfiguration().get(ConfigKeys.ACTIVITY);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
