package com.petterp.latte.ec.detail;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.petterp.latte.ec.R;
import com.petterp.latte_ui.retyclear.ItemType;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * Created by
 */

public class RecyclerImageAdapter extends MultipleRecyclearAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();

    protected RecyclerImageAdapter(List<MulitpleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        final int type = holder.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView imageView = holder.getView(R.id.image_rv_item);
                final String url = entity.getField(MultipleFidls.IMAGE_URL);
                Glide.with(mContext)
                        .load(url)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
