package com.petterp.latte_core.delegates.bottom;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.petterp.latte.R;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.delegates.LatteDelegate;

/**
 * @author Petterp on 2019/4/22
 * Summary:每一个页面(或者说每一个子项的具体超类)->抽象基类
 * 邮箱：1509492795@qq.com
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplication().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
