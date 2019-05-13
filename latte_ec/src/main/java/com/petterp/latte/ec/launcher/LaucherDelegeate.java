package com.petterp.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte_core.app.AccouttManager;
import com.petterp.latte_core.app.IUserCheker;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.launcher.ILauncherListener;
import com.petterp.latte_ui.launcher.OnLauncherFinishTag;
import com.petterp.latte_ui.launcher.ScrollLauncherTag;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_core.util.timer.BaseTimerTask;
import com.petterp.latte_core.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Petterp on 2019/4/20
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class LaucherDelegeate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer=null;
    //倒计时时间
    private int mCount=5;
    private ILauncherListener mILauncherListener=null;


    //点击事件
    @OnClick(R2.id.tv_launcher_timer)
    void onCliclTimerView() {
        if (mTimer!=null){
            mTimer.cancel();
            mTimer=null;
            checkIsShowScroll();
        }
    }



    /**
     * 开始
     */
    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask task=new BaseTimerTask(this);
        //第一个参数马上开始，第二位间隔时间，延迟时间
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener= (ILauncherListener) activity;
        }
    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        initTimer();
    }


    /**
     * 是否显示滑动启动页
     */
    private void checkIsShowScroll(){
        if (!LatterPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            //退栈再启动一个新的
            getSupportDelegate().startWithPop(new LaunchaerScrollDelegeate());
        }else{
            //检查用户是否登录了App
            AccouttManager.checkAccount(new IUserCheker() {
                @Override
                //有用户信息
                public void onSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                //没有用户信息
                public void onNotSoignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer=null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
