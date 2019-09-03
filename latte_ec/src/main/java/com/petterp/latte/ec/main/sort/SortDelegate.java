package com.petterp.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte.ec.R2;
import com.petterp.latte.ec.main.sort.content.ContentDelegate;
import com.petterp.latte.ec.main.sort.list.VerticalListDelegate;
import com.petterp.latte_core.delegates.bottom.BottomItemDelegate;
import com.petterp.latte_core.util.Bootomzhaungtai.EachTooblar;

import butterknife.BindView;

/**
 * @author Petterp on 2019/4/22
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SortDelegate extends BottomItemDelegate {
    @BindView(R2.id.sort_toolbar)
    Toolbar mToolbar = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }


    //点击之后才进行加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        EachTooblar.Client.init(false);
        final VerticalListDelegate listDelegate=new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);
        //设置右侧第一个分类显示，默认显示分类1
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,ContentDelegate.newInstance(1),false,false);
    }
}
