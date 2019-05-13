package com.petterp.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte.ec.detall.GoodsDetaillDeleagte;
import com.petterp.latte_core.delegates.LatteDelegate;

/**
 * @author Petterp on 2019/4/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class IndexItemClickListener extends SimpleClickListener {
    private final LatteDelegate DELEGATE;

    public IndexItemClickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    public static SimpleClickListener create(LatteDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetaillDeleagte deleagte=GoodsDetaillDeleagte.create();
        DELEGATE.getSupportDelegate().start(deleagte);
    }


    //长点击
    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
