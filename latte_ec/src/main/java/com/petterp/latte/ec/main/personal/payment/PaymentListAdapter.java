package com.petterp.latte.ec.main.personal.payment;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte.ec.R;
import com.petterp.latte.ec.main.cart_gouwuche.ShopCartItemFields;
import com.petterp.latte.ec.main.personal.order.OrderItemFields;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
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
        addItemType(PaymentListItemType.ITEM_PAYMENT_LIST, R.layout.item_paymenu_list);
    }

    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case PaymentListItemType.ITEM_PAYMENT_LIST:
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_payment_cart);
                final AppCompatImageView imageView = holder.getView(R.id.image_payment_list);
                final AppCompatTextView title = holder.getView(R.id.tv_payment_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_payment_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_payment_list_time);

                final boolean isSelected = entity.getField(PaymentFields.IS_SELECTED);
                final String titleVal = entity.getField(MultipleFidls.TITLE);
                final String timeVal = entity.getField(PaymentFields.TIME);
                final double priceVal = entity.getField(PaymentFields.PRICE);
                final String imageVal = entity.getField(MultipleFidls.IMAGE_URL);
                title.setText(titleVal);
                price.setText("价格：" + String.valueOf(priceVal));
                time.setText("时间：" + timeVal);
                Glide.with(mContext)
                        .load(imageVal)
                        .apply(REQUEST_OPTIONS)
                        .into(imageView);
                //模拟左侧点击事件
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                iconIsSelected.setOnClickListener(v -> {
                    final boolean currentSelected = entity.getField(PaymentFields.IS_SELECTED);
                    if (currentSelected) {
                        iconIsSelected.setTextColor(Color.GRAY);
                        entity.setFild(PaymentFields.IS_SELECTED, false);
                    } else {
                        iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
                        entity.setFild(PaymentFields.IS_SELECTED, true);
                    }
                });
                break;
            default:
                break;
        }
    }
}
