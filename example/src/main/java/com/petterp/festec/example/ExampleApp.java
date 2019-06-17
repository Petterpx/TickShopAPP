package com.petterp.festec.example;

import android.app.Application;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.petterp.latte.ec.database.DatabaseManager;
import com.petterp.latte.ec.icon.FontEcModule;
import com.petterp.latte_core.app.Latte;
import com.petterp.festec.example.event.TestEvent;
import com.petterp.latte_core.net.interceptors.DebugInterceptor;
import com.petterp.latte_core.util.callback.CallbackManager;
import com.petterp.latte_core.util.callback.CallbackType;
import com.petterp.latte_core.util.callback.IGlobalCallback;

import cn.jpush.android.api.JPushInterface;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Handler handler=new Handler();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://101.132.64.249:80")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withHandler(handler)
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .configure();
//        initStetho();
        DatabaseManager.getInstance().init(this);
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, args -> {
                    if (JPushInterface.isPushStopped(Latte.getApplication())) {
                        //开启极光推送
                        JPushInterface.setDebugMode(true);
                        JPushInterface.init(Latte.getApplication());
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, args -> {
                    if (!JPushInterface.isPushStopped(Latte.getApplication())) {
                        //停止极光推送
                        JPushInterface.stopPush(Latte.getApplication());
                    }
                });
    }

    /**
     * facebook可视化数据库
     */
    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}

