package com.petterp.latte_core.wechat.templates;


import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.wechat.BaseWXEntryActivity;
import com.petterp.latte_core.wechat.LatteWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {


    /**
     * 再次进入时杀掉Activity,并关闭动画，相当于透明Activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }

}
