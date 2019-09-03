package com.petterp.latte.ec.main.cart_gouwuche;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.petterp.latte.ec.R;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author Petterp on 2019/4/28
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class ShopCartAdapter extends MultipleRecyclearAdapter {

    private boolean mIsSelectedAll = false;
    //接口传递消息给View去处理数据
    private IcartItemListener mIcartItemListener = null;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .fitCenter()
            .dontAnimate();

    ShopCartAdapter(List<MulitpleItemEntity> data) {
        super(data);
        //添加子布局个数 item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    void setmIcartItemListener(IcartItemListener listener) {
        this.mIcartItemListener = listener;
    }

    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //先取出所有值
                final int id = entity.getField(MultipleFidls.ID);
                final String thumb = entity.getField(MultipleFidls.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);
                //在左侧勾是否全选之前改变状态
//                entity.setFild(ShopCartItemFields.IS_SE LECTED, mIsSelectedAll);
                //实时状态
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                //根据数据状态显示左侧勾
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                entity.setFild(ShopCartItemFields.POSITION,holder.getAdapterPosition());
                //添加左勾点击事件
                iconIsSelected.setOnClickListener(v -> {
                    final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                    final int countCont = entity.getField(ShopCartItemFields.COUNT);
                    if (currentSelected) {
                        iconIsSelected.setTextColor(Color.GRAY);
                        entity.setFild(ShopCartItemFields.IS_SELECTED, false);
                    } else {
                        iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
                        entity.setFild(ShopCartItemFields.IS_SELECTED, true);
                    }
                    entity.setFild(ShopCartItemFields.POSITION, holder.getAdapterPosition());
                    mIcartItemListener.onItemClick();
                });

                //添加加减事件
                iconMinus.setOnClickListener(v -> {
                    final int surrentCount = entity.getField(ShopCartItemFields.COUNT);
                    //判断，大于1才可以减
                    if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                        int countNum = Integer.parseInt(tvCount.getText().toString());
                        countNum--;
                        tvCount.setText(String.valueOf(countNum));
                        entity.setFild(ShopCartItemFields.COUNT, countNum);
                        //接口回调，驱动View更新数据
                        if (mIcartItemListener != null) {
                            mIcartItemListener.onItemClick();
                        }
                    } else {
                        Toast.makeText(mContext, "该宝贝不能减少了呦~", Toast.LENGTH_SHORT).show();
                    }
                });

                iconPlus.setOnClickListener(v -> {
                    final int surrentCount = entity.getField(ShopCartItemFields.COUNT);
                    //判断，大于1才可以减
                    if (Integer.parseInt(tvCount.getText().toString()) < 99) {
                        int countNum = Integer.parseInt(tvCount.getText().toString());
                        countNum++;
                        tvCount.setText(String.valueOf(countNum));
                        entity.setFild(ShopCartItemFields.COUNT, countNum);
                        //接口回调，驱动View更新数据
                        if (mIcartItemListener != null) {
                            mIcartItemListener.onItemClick();
                        }
                    } else {
                        Toast.makeText(mContext, "该宝贝已经达上限啦~", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }

}
