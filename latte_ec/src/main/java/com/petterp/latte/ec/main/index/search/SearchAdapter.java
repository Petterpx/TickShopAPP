package com.petterp.latte.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.petterp.latte.ec.R;
import com.petterp.latte_ui.retyclear.MulitipleViewHolder;
import com.petterp.latte_ui.retyclear.MulitpleItemEntity;
import com.petterp.latte_ui.retyclear.MultipleFidls;
import com.petterp.latte_ui.retyclear.MultipleRecyclearAdapter;

import java.util.List;

/**
 * @author Petterp on 2019/6/18
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class SearchAdapter extends MultipleRecyclearAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SearchAdapter(List<MulitpleItemEntity> data) {
        super(data);
        addItemType(SerachType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    public void convert(MulitipleViewHolder holder, MulitpleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SerachType.ITEM_SEARCH:
                final AppCompatTextView textView = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFidls.TEXT);
                textView.setText(history);
                break;
            default:
                break;
        }
    }
}
