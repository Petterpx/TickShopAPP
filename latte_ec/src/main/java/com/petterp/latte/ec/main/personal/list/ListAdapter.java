package com.petterp.latte.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.chrisbanes.photoview.PhotoView;
import com.petterp.latte.ec.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Petterp on 2019/4/30
 * Summary:数据转化
 * 邮箱：1509492795@qq.com
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_BORNAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avator);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
    }

    public IListOnclick listOnclick;

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_BORNAL:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;
            case ListItemType.ITEM_AVATAR:
                RelativeLayout relativeLayout = helper.itemView.findViewById(R.id.rv_arrow_layout);
                CircleImageView circleImageView = helper.itemView.findViewById(R.id.img_arrow_avatar);
                relativeLayout.setOnClickListener(v -> {
                    listOnclick.Rvonclik();
                });
                circleImageView.setOnClickListener(v -> {
                    listOnclick.Imonclick();
                });
                Glide.with(mContext)
                        .load(item.getmImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            case ListItemType.ITEM_SWITCH:
                helper.setText(R.id.tv_arrow_switch_text, item.getmText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getmOnCheckedChangeListener());
                break;
            default:
                break;
        }
    }
}
