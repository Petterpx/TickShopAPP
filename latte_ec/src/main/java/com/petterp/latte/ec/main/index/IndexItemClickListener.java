package com.petterp.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte.ec.detail.GoodsDetailDelegate;
import com.petterp.latte.ec.detail.GoodsInfoDelegate;
import com.petterp.latte.ec.main.personal.address.AddresDelegate;
import com.petterp.latte.ec.main.personal.list.ListBean;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;

/**
 * @author Petterp on 2019/4/27
 * Summary:item的点击事件
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
        final MulitpleItemEntity entity= (MulitpleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId=entity.getField(MultipleFidls.ID);
        //传递ID
        final GoodsDetailDelegate deleagte=GoodsDetailDelegate.create(goodsId);
//        DELEGATE.getSupportDelegate().start(deleagte);
        DELEGATE.getParentDelegate().getSupportDelegate().start(deleagte);

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
