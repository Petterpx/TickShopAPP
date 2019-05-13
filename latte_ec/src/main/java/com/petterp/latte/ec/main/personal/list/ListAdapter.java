package com.petterp.latte.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.petterp.latte.ec.R;

import java.util.List;

/**
 * @author Petterp on 2019/4/30
 * Summary:数据转化
 * 邮箱：1509492795@qq.com
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_BORNAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_BORNAL:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value,item.getmValue());
                break;
            default:
                break;
        }
    }
}
