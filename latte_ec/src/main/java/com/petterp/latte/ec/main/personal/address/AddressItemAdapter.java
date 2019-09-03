package com.petterp.latte.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.petterp.latte.ec.R;
import com.petterp.latte_core.net.RestClient;
import com.petterp.latte_core.net.callBack.ISuccess;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author Petterp on 2019/6/16
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class AddressItemAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected AddressItemAdapter(List<MulitpleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFidls.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFidls.TAG);
                final int id = entity.getField(MultipleFidls.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                //图标右上角删除事件
                deleteTextView.setOnClickListener(v -> RestClient.builder()
                        .url("data/address.json")
                        .params("id", id)
                        .success(response ->{
                            //删除掉相应的item
                            remove(holder.getLayoutPosition());
                        })
                        .build()
                        .post());

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
