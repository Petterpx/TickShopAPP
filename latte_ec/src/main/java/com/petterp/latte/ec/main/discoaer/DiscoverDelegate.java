package com.petterp.latte.ec.main.discoaer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.delegates.web.WebDelegate;
import com.petterp.latte_core.delegates.web.WebDelegateImpl;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //http://demo1.mycodes.net/youxi/qingwacangying/
        final WebDelegateImpl delegate=WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container,delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
