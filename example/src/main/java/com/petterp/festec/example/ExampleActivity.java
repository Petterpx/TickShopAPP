package com.petterp.festec.example;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.petterp.latte.ec.launcher.LaucherDelegeate;
import com.petterp.latte.ec.main.EcBottomDelgate;
import com.petterp.latte.ec.sign.ISignListener;
import com.petterp.latte.ec.sign.SignInDelegate;
import com.petterp.latte.ec.sign.SignUpDelegate;
import com.petterp.latte_core.activity.ProxyActivity;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.launcher.ILauncherListener;
import com.petterp.latte_ui.launcher.OnLauncherFinishTag;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

import static me.yokeyword.fragmentation.ISupportFragment.SINGLETASK;


public class ExampleActivity extends ProxyActivity implements ISignListener
        ,ILauncherListener
{

    @Override
    public LatteDelegate setRootDelegate() {
        return new LaucherDelegeate();
    }

    //隐藏actionbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }



    /**
     *   回调接口-> LaunchaerScrollDelegeate ，登录了怎样怎样，没有登录怎样怎样
     * @param tag
     */
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        if (tag==OnLauncherFinishTag.SIGNED){
//            getSupportDelegate().pop();
//            getSupportDelegate().pop();
//            getSupportDelegate().startWithPop(new EcBottomDelgate());
            getSupportDelegate().start(new EcBottomDelgate(),SINGLETASK);

        }else{
            //退栈并启动
            getSupportDelegate().startWithPop(new SignInDelegate());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onPause(this);
    }
}
