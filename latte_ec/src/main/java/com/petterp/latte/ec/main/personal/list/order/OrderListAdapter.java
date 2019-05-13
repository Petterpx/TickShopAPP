package com.petterp.latte.ec.main.personal.list.order;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.petterp.latte.ec.R;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author Petterp on 2019/4/30
 * Summary:绑定视图
 * 邮箱：1509492795@qq.com
 */
public class OrderListAdapter extends MultipleRecyclearAdapter {

    protected OrderListAdapter(List<MulitpleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .fitCenter()
            .dontAnimate();

    @SuppressLint("SetTextI18n")
    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView =holder.getView(R.id.image_order_list);
                final AppCompatTextView title=holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price=holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time=holder.getView(R.id.tv_order_list_time);

                final String titleVal=entity.getField(MultipleFidls.TITLE);
                final String timeVal=entity.getField(OrderItemFields.TIME);
                final double priceVal=entity.getField(OrderItemFields.PRICE);
                final String  imageVal=entity.getField(MultipleFidls.IMAGE_URL);
                title.setText(titleVal);
                price.setText("价格："+String.valueOf(priceVal));
                time.setText("时间："+timeVal);
                Glide.with(mContext)
                        .load(imageVal)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            default:
                break;
        }
    }

}
