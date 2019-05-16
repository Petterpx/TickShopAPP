package com.petterp.latte.ec.main.personal.list.payment;

import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleEntityBuilder;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author Petterp on 2019/05/17
 * Summary:适配器
 * 邮箱：1509492795@qq.com
 */
public class PaymentListAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected PaymentListAdapter(List<MulitpleItemEntity> data) {
        super(data);
    }
}
